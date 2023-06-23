package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
import com.ahom.hrms.service.WorkInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class WorkInformationServiceImpl implements WorkInformationService {

    @Autowired
    WorkInformationRepository workInformationRepository;

    @Autowired
    BasicEmployeeRepository basicEmployeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JavaMailSender mailSender;
    @Value("${mail.from}")
    private String fromEmail; // Set from email address in application.properties file

//    @Value("${mail.hr}")
     String hrEmail="malikpradeep2001@gmail.com"; // Set HR email address in application.properties file

//    @Value("${mail.subject}")
//    private String emailSubject;

    //save data
    public WorkInformation saveWorkInfo(WorkInformationDto workInformationDto) throws Exception {

         return workInformationRepository.
                 save(workInformationDtoToWorkInformation(workInformationDto));


    }

    //converting DTO
    public WorkInformation workInformationDtoToWorkInformation(WorkInformationDto workInformationDto) throws Exception {
        WorkInformation workInformation = this.modelMapper.map(workInformationDto, WorkInformation.class);
        BasicEmployee basicEmployee=basicEmployeeRepository.findById(workInformationDto.getWorkId()).orElse(null);
        WorkInformation workInformation1=workInformationRepository.findById(workInformationDto.getWorkId()).orElse(null);
        if(workInformation1==null)
        {
            if (basicEmployee!=null) {
                workInformation.setWorkId(workInformationDto.getWorkId());
                workInformation.setBasicEmployee(basicEmployee);
            }else {
                throw new RuntimeException("employee not found for particular Id" +" "+workInformation.getWorkId());
            }
        }
        else
        {
            throw new RuntimeException("Work information already present for Employee:"+
                    workInformationDto.getEmployeeName());
        }
        return workInformation;
    }

    public WorkInformationDto workInformationToWorkInformationDto(WorkInformation workInformation) {
        return this.modelMapper.map(workInformation, WorkInformationDto.class);
    }


    //fetch
    public List getAll() {
        return workInformationRepository.findAll();
    }


    @Scheduled(cron = "00 24 12 * * ?")
    public void checkProbation() throws MessagingException {
        List<WorkInformation>workInformation=workInformationRepository.findAll();

        LocalDate localDate=LocalDate.now();

        for (WorkInformation workInformation1:workInformation)
        {
            if (workInformation1.getEmploymentType().equals("probation")) {
                LocalDate joiningDate = LocalDate.parse(workInformation1.getBasicEmployee().getJoiningDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                LocalDate probationEndDate = joiningDate.plusMonths(6);
                long daysToComplete = ChronoUnit.DAYS.between(localDate, probationEndDate);
                if (daysToComplete==7 ||daysToComplete==0) {


                    MimeMessage message=mailSender.createMimeMessage();
                    MimeMessageHelper simpleMailMessageToHr=new MimeMessageHelper(message);
                    simpleMailMessageToHr.setFrom(fromEmail);
                    simpleMailMessageToHr.setTo(hrEmail);
                    simpleMailMessageToHr.setSubject("Probation End Reminder");
                    simpleMailMessageToHr.setText("Probation period of employee "
                            + workInformation1.getBasicEmployee().getEmployeeName()
                            + " will end in one week.");
                    mailSender.send(message);
                    System.out.println(message);

                    MimeMessageHelper messageToEmployee=new MimeMessageHelper(message);
                    messageToEmployee.setFrom(fromEmail);
                    messageToEmployee.setTo(workInformation1.getBasicEmployee().getEmail());
                    messageToEmployee.setSubject("Probation End Reminder");
                    messageToEmployee.setText("Your probation period will end in 7 days. Please contact HR for further instructions.");
                    mailSender.send(message);
                    System.out.println(message);
                }
            }



            }
        }



}
