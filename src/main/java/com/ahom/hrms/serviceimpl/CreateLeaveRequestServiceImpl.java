package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Repository.LeaveRecordRepository;
import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.entities.*;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.CreateLeaveRequestService;
import com.ahom.hrms.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateLeaveRequestServiceImpl implements CreateLeaveRequestService{

	@Autowired
	CreateLeaveRequestRepository createLeaveRequestRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail;

	@Autowired
	private BasicEmployeeRepository basicEmployeeRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	LeaveRecordRepository leaveRecordRepository;
	@Autowired
	NotificationService notificationService;

	LeaveRecord leaveRecord;

	@Override
	public CreateLeaveRequest saveCreateLeaveRequest(CreateLeaveRequest createLeaveRequest) throws ParseException {
		Employee employee = employeeRepository.findById(createLeaveRequest.getId()).orElse(null);
		LeaveRecord leaveRecord1=leaveRecordRepository.findById(createLeaveRequest.getId()).orElse(null);

		Notification notification=new Notification();
		if (employee!=null) {
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String startDateString = createLeaveRequest.getStartDate();
			String endDateString = createLeaveRequest.getEndDate();
			LocalDate startDate = LocalDate.parse(startDateString, formatter);
			LocalDate endDate = LocalDate.parse(endDateString, formatter);

			long daysInBetween= ChronoUnit.DAYS.between(startDate,endDate);
			long finalDays = daysInBetween + 1;
			System.out.println("daysInBetwwnnnnnnn : "+daysInBetween);
			System.out.println("finalDaysssss : "+finalDays);

			if (currentDate.isEqual(startDate) || startDate.isAfter(currentDate)) {
				if (endDate.isAfter(startDate) || endDate.isAfter(currentDate) || endDate.isEqual(startDate)) {
					createLeaveRequest.setEmail(employee.getUsername());
					createLeaveRequest.setStatus("3");
					createLeaveRequest.setNoOfDays(finalDays);
					createLeaveRequest.setLeaveRecord(leaveRecord1);
					notification.setMessage("New leave request from Employee: " + createLeaveRequest.getEmail());
					notification.setStatus(true);
					notificationService.saveNotification(notification);
					createLeaveRequestRepository.save(createLeaveRequest);
				}else {
					throw new CustomException("End date cannot be earlier than current date or before start date ");
				}
			}
			else {
				throw new RuntimeException("Start date cannot be earlier than the current date");
			}
		}

		else {
			throw new RuntimeException("no employee");
		}
		return createLeaveRequest;
	}

	@Override
	public List<CreateLeaveRequestDto> getAllCreateLeaveRequest(){
		List<CreateLeaveRequest> listCreateLeaveRequest= this.createLeaveRequestRepository.findAll();
		return listCreateLeaveRequest.stream().map(this::createLeaveRequesttoCreateLeaveRequestdto).collect(Collectors.toList());
	}


	@Override
	public CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto,String id) {
		CreateLeaveRequest createLeaveRequest=createLeaveRequestRepository.findById(id).orElse(null);

		if(createLeaveRequest!=null)
		{
			createLeaveRequest.setStatus(createLeaveRequestDto.getStatus());
			createLeaveRequestRepository.saveAndFlush(createLeaveRequest);
			if(createLeaveRequestDto.getStatus().equals("1"))
			{
				try {
					MimeMessage message=mailSender.createMimeMessage();
					MimeMessageHelper messageToEmployee=new MimeMessageHelper(message);
					messageToEmployee.setFrom(fromEmail);
					messageToEmployee.setTo(createLeaveRequest.getEmail());
					messageToEmployee.setSubject("Response Against Leave Request");
					messageToEmployee.setText("Request for leave");
					mailSender.send(message);
					System.out.println(message);

					if(createLeaveRequest.getNoOfDays()>=createLeaveRequest.getLeaveRecord().getTotalLeave())
					{
						LeaveRecord leaveRecord1=new LeaveRecord();
						leaveRecord1.setId(createLeaveRequest.getLeaveRecord().getId());
						leaveRecord1.setEmployeeName(createLeaveRequest.getLeaveRecord().getEmployeeName());
						leaveRecord1.setLop(createLeaveRequest.getNoOfDays()-createLeaveRequest.getLeaveRecord().getTotalLeave()+ createLeaveRequest.getLeaveRecord().getLop());
						leaveRecord1.setTotalLeave(0);
						leaveRecord1.setLeaveLeft(0);
						leaveRecord1.setLeaveTaken(createLeaveRequest.getNoOfDays()+ createLeaveRequest.getLeaveRecord().getLeaveTaken());
						leaveRecordRepository.save(leaveRecord1);
						createLeaveRequestDto.setLeaveRecord(leaveRecord1);
					}
					else {
						LeaveRecord leaveRecord1=new LeaveRecord();
						leaveRecord1.setId(createLeaveRequest.getLeaveRecord().getId());
						leaveRecord1.setEmployeeName(createLeaveRequest.getLeaveRecord().getEmployeeName());
						leaveRecord1.setLop(0);
						leaveRecord1.setTotalLeave(createLeaveRequest.getLeaveRecord().getTotalLeave()- createLeaveRequest.getNoOfDays());
						leaveRecord1.setLeaveLeft(createLeaveRequest.getLeaveRecord().getTotalLeave()-createLeaveRequest.getNoOfDays());
						leaveRecord1.setLeaveTaken(createLeaveRequest.getNoOfDays());
						leaveRecordRepository.save(leaveRecord1);
						createLeaveRequestDto.setLeaveRecord(leaveRecord1);
					}
				}catch (RuntimeException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}
			else if (createLeaveRequestDto.getStatus().equals("2"))
			{
				try {
					MimeMessage message=mailSender.createMimeMessage();
					MimeMessageHelper helper=new MimeMessageHelper(message);
					helper.setFrom(fromEmail);
					helper.setTo(createLeaveRequest.getEmail());
					helper.setSubject("Response Against Leave Request");
					helper.setText("Request For Leave");

					mailSender.send(message);
					System.out.println(message);
				}catch (RuntimeException e)
				{
					e.printStackTrace();
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}
			else {
				return null;
			}
		}
		else {
			throw new RuntimeException("Create Leave Not Found for Id : "+id);
		}
		return createLeaveRequestDto;
	}

	public CreateLeaveRequest createLeaveRequestdtotoCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
				return this.modelMapper.map(createLeaveRequestDto,CreateLeaveRequest.class);
	}

	public CreateLeaveRequestDto createLeaveRequesttoCreateLeaveRequestdto(CreateLeaveRequest createLeaveRequest)
	{
		return this.modelMapper.map(createLeaveRequest,CreateLeaveRequestDto.class);
	}

}
