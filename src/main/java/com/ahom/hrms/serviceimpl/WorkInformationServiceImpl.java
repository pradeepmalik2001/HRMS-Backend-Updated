package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.WorkInformationService;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


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

    @Value("${mail.subject}")
    private String emailSubject;

    //save data
    public void saveWorkInfo(WorkInformationDto workInformationDto) throws Exception {

            workInformationRepository.save(workInformationDtoToWorkInformation(workInformationDto));
           
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
                throw new CustomException("employee not found for particular Id" +" "+workInformation.getWorkId());
            }
        }else
        {
            throw new CustomException("Record for Id" + " " + workInformationDto.getWorkId()+" " + "is already present");
        }
        return workInformation;
    }

    public WorkInformationDto workInformationToWorkInformationDto(WorkInformation workInformation) {
        return this.modelMapper.map(workInformation, WorkInformationDto.class);
    }


    //fetch
    public List<WorkInformationDto> getAll() {
        List list=workInformationRepository.findAll();
        return list;
    }


    @Scheduled(cron = "40 47 10 * * ?")
    public void checkProbation()
    {
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
                    SimpleMailMessage simpleMailMessageToHr = new SimpleMailMessage();

                    simpleMailMessageToHr.setFrom(fromEmail);
                    simpleMailMessageToHr.setFrom(fromEmail);
                    simpleMailMessageToHr.setTo(hrEmail);
                    simpleMailMessageToHr.setSubject(emailSubject);
                    simpleMailMessageToHr.setText("Probation period of employee " + workInformation1.getBasicEmployee().getEmployeeName() + " will end in one week.");
                    mailSender.send(simpleMailMessageToHr);

                    System.out.println(simpleMailMessageToHr);
                    SimpleMailMessage messageToEmployee = new SimpleMailMessage();
                    messageToEmployee.setFrom(fromEmail);
                    messageToEmployee.setTo(workInformation1.getBasicEmployee().getEmail());
                    messageToEmployee.setSubject(emailSubject);
                    messageToEmployee.setText("Your probation period will end in 7 days. Please contact HR for further instructions.");
                    mailSender.send(messageToEmployee);
                    System.out.println(messageToEmployee);
                }
            }


//                {
//                    String employeeEmail=workInformation1.getBasicEmployee().getEmail();
//                    String hrEmail="harshniranjan@gmail.com";
//                    String subject="Probation Period Ending Soon";
//                    String text="Dear " + workInformation1.getBasicEmployee().getEmployeeName() +
//                            ",\n\nYour probation period will end in two days. " +
//                            "Please contact HR for further instructions.\n\nBest regards,\nHR department";
//                    try {
//                        sendMail(employeeEmail,subject,text);
//                    }catch (MappingException e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                }
            }
        }


//    private void sendMail(String employeeEmail, String subject, String text) {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(to);
//        helper.setCc(cc);
//        helper.setSubject(subject);
//        helper.setText(text);
//        mailSender.send(message);
//
//
//
//    }


}
