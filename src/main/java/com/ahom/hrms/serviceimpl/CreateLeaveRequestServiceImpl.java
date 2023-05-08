package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.CreateLeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.CreateLeaveRequestDto;

import com.ahom.hrms.service.CreateLeaveRequestService;

@Service
public class CreateLeaveRequestServiceImpl implements CreateLeaveRequestService{

	@Autowired
	CreateLeaveRequestRepository createLeaveRequestRepository;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail; // Set from email address in application.properties file

	//    @Value("${mail.hr}")
//	String hrEmail="malikpradeep2001@gmail.com"; // Set HR email address in application.properties file

	@Value("${mail.subject}")
	private String emailSubject;

	@Override
	public void saveCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {

		String fromEmail=createLeaveRequestDto.getBasicEmployee().getEmail();
		SimpleMailMessage messageToEmployee = new SimpleMailMessage();
		messageToEmployee.setFrom(fromEmail);
		messageToEmployee.setTo("pradeep.malik@skillzamp.com");
		messageToEmployee.setSubject(emailSubject);
		messageToEmployee.setText("Request for leave");
		mailSender.send(messageToEmployee);
		System.out.println(messageToEmployee);
		createLeaveRequestRepository.save(createLeaveRequestdtotoCreateLeaveRequest(createLeaveRequestDto));

	}

	@Override
	public List<CreateLeaveRequestDto> getAllCreateLeaveRequest(){
		List<CreateLeaveRequest> listCreateLeaveRequest= this.createLeaveRequestRepository.findAll();
		List<CreateLeaveRequestDto> createLeaveRequestDtoList = listCreateLeaveRequest.stream().map(emp -> this.createLeaveRequesttoCreateLeaveRequestdto(emp)).collect(Collectors.toList());
		//employeeReposatory.findAll().forEach(l1->listEmployee.add(l1));
		return createLeaveRequestDtoList;
	}

	//	 public void deleteCreateLeaveRequest(int empId){
//
//	        createLeaveRequestRepository.deleteById(empId);
//
//	    }
	@Override
	public CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
		createLeaveRequestRepository.save(createLeaveRequestdtotoCreateLeaveRequest(createLeaveRequestDto));
		return createLeaveRequestDto;

	}

	public CreateLeaveRequest createLeaveRequestdtotoCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
		CreateLeaveRequest createLeaveRequest=new CreateLeaveRequest();


//	        createLeaveRequest.setAvailableBalance(createLeaveRequestDto.getAvailableBalance());
		createLeaveRequest.setId(createLeaveRequestDto.getId());
//		createLeaveRequest.setDays(createLeaveRequestDto.getDays());
		createLeaveRequest.setEndDate(createLeaveRequestDto.getEndDate());
		createLeaveRequest.setLeaveApprover(createLeaveRequestDto.getLeaveApprover());
//	        createLeaveRequest.setLeaveFor(createLeaveRequestDto.getLeaveFor());
		createLeaveRequest.setLeaveType(createLeaveRequestDto.getLeaveType());
		createLeaveRequest.setReasonForLeave(createLeaveRequestDto.getReasonForLeave());
		createLeaveRequest.setSelectEmployee(createLeaveRequestDto.getSelectEmployee());
		createLeaveRequest.setStartDate(createLeaveRequestDto.getStartDate());
		createLeaveRequest.setApprove(createLeaveRequestDto.isApprove());


		return createLeaveRequest;
	}

	public CreateLeaveRequestDto createLeaveRequesttoCreateLeaveRequestdto(CreateLeaveRequest createLeaveRequest) {
		CreateLeaveRequestDto createLeaveRequestDto= new CreateLeaveRequestDto();

//		  createLeaveRequestDto.setAvailableBalance(createLeaveRequest.getAvailableBalance());
//		createLeaveRequestDto.setDays(createLeaveRequest.getDays());
		createLeaveRequestDto.setEndDate(createLeaveRequest.getEndDate());
		createLeaveRequestDto.setLeaveApprover(createLeaveRequest.getLeaveApprover());
//		  createLeaveRequestDto.setLeaveFor(createLeaveRequest.getLeaveFor());
		createLeaveRequestDto.setLeaveType(createLeaveRequest.getLeaveType());
		createLeaveRequestDto.setReasonForLeave(createLeaveRequest.getReasonForLeave());
		createLeaveRequestDto.setSelectEmployee(createLeaveRequest.getSelectEmployee());
		createLeaveRequestDto.setStartDate(createLeaveRequest.getStartDate());
		createLeaveRequestDto.setApprove(createLeaveRequest.isApprove());
		return createLeaveRequestDto;

	}

}
