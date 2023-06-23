package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.CreateLeaveRequest;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.CreateLeaveRequestService;
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

//	@Value("${mail.subject}")
//	private String emailSubject;
	@Autowired
	private BasicEmployeeRepository basicEmployeeRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public CreateLeaveRequest saveCreateLeaveRequest(CreateLeaveRequest createLeaveRequest) throws ParseException {
		Employee employee = employeeRepository.findById(createLeaveRequest.getId()).orElse(null);

		if (employee!=null) {
//			Date currentDate = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			String startDateString = createLeaveRequest.getStartDate();
//			String endDateString = createLeaveRequest.getEndDate();
//			Date startDate = formatter.parse(startDateString);
//			Date endDate = formatter.parse(endDateString);
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String startDateString = createLeaveRequest.getStartDate();
			String endDateString = createLeaveRequest.getEndDate();
			LocalDate startDate = LocalDate.parse(startDateString, formatter);
			LocalDate endDate = LocalDate.parse(endDateString, formatter);

			if (currentDate.isEqual(startDate) || startDate.isAfter(currentDate)) {
				if (endDate.isAfter(startDate) || endDate.isAfter(currentDate) || endDate.isEqual(startDate)) {
					createLeaveRequest.setEmail(employee.getUsername());
					createLeaveRequest.setStatus("3");
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
				}catch (RuntimeException e)
				{
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

		//		createLeaveRequest.setStatus("3");
		return this.modelMapper.map(createLeaveRequestDto,CreateLeaveRequest.class);
	}

	public CreateLeaveRequestDto createLeaveRequesttoCreateLeaveRequestdto(CreateLeaveRequest createLeaveRequest)
	{
		return this.modelMapper.map(createLeaveRequest,CreateLeaveRequestDto.class);
	}

}
