package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.CreateLeaveRequest;
import com.ahom.hrms.exception.CustomException;
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
	private String fromEmail;

	@Value("${mail.subject}")
	private String emailSubject;
	@Autowired
	private BasicEmployeeRepository basicEmployeeRepository;

	@Override
	public void saveCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
		BasicEmployee basicEmployee=basicEmployeeRepository.findById(createLeaveRequestDto.getId()).orElse(null);

		if (basicEmployee!=null) {
			createLeaveRequestRepository.save(createLeaveRequestdtotoCreateLeaveRequest(createLeaveRequestDto));
			try {


//				String fromEmail =

//			String fromEmail = createLeaveRequestDto.getBasicEmployee().getEmail();
				SimpleMailMessage messageToEmployee = new SimpleMailMessage();
				messageToEmployee.setFrom(fromEmail);
				messageToEmployee.setTo("pradeep.malik@skillzamp.com");
				messageToEmployee.setSubject(emailSubject);
				messageToEmployee.setText("Request for leave");
				mailSender.send(messageToEmployee);
				System.out.println(messageToEmployee);
			}catch (RuntimeException e)
			{
				e.printStackTrace();
			}

		}
		else {
			throw new CustomException("no employee");
		}

	}

	@Override
	public List<CreateLeaveRequestDto> getAllCreateLeaveRequest(){
		List<CreateLeaveRequest> listCreateLeaveRequest= this.createLeaveRequestRepository.findAll();
		return listCreateLeaveRequest.stream().map(this::createLeaveRequesttoCreateLeaveRequestdto).collect(Collectors.toList());
	}


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
