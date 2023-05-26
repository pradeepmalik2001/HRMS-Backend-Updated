package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.CreateLeaveRequest;
import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
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
	ModelMapper modelMapper;

	@Autowired
	JavaMailSender mailSender;
	@Value("${mail.from}")
	private String fromEmail;

	@Value("${mail.subject}")
	private String emailSubject;
	@Autowired
	private BasicEmployeeRepository basicEmployeeRepository;

	@Override
	public CreateLeaveRequestDto saveCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
		BasicEmployee basicEmployee=basicEmployeeRepository.findById(createLeaveRequestDto.getId()).orElse(null);

		if (basicEmployee!=null) {
			createLeaveRequestDto.setEmail(basicEmployee.getEmail());
			createLeaveRequestDto.setStatus("3");
			createLeaveRequestRepository.save(createLeaveRequestdtotoCreateLeaveRequest(createLeaveRequestDto));
		}
		else {
			throw new RuntimeException("no employee");
		}
		return createLeaveRequestDto;
	}

	@Override
	public List<CreateLeaveRequestDto> getAllCreateLeaveRequest(){
		List<CreateLeaveRequest> listCreateLeaveRequest= this.createLeaveRequestRepository.findAll();
		return listCreateLeaveRequest.stream().map(this::createLeaveRequesttoCreateLeaveRequestdto).collect(Collectors.toList());
	}


	@Override
	public CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto,int id) {
		CreateLeaveRequest createLeaveRequest=createLeaveRequestRepository.findById(id).orElse(null);
		if(createLeaveRequest!=null)
		{
//			createLeaveRequest.setSelectEmployee(createLeaveRequestDto.getSelectEmployee());
//			createLeaveRequest.setLeaveApprover(createLeaveRequestDto.getLeaveApprover());
//			createLeaveRequest.setLeaveType(createLeaveRequestDto.getLeaveType());
//			createLeaveRequest.setStartDate(createLeaveRequestDto.getStartDate());
//			createLeaveRequest.setEndDate(createLeaveRequestDto.getEndDate());
//			createLeaveRequest.setReasonForLeave(createLeaveRequestDto.getReasonForLeave());
			createLeaveRequest.setStatus(createLeaveRequestDto.getStatus());
			createLeaveRequestRepository.saveAndFlush(createLeaveRequest);
			if(createLeaveRequestDto.getStatus().equals("1"))
			{
				try {
					SimpleMailMessage messageToEmployee = new SimpleMailMessage();
					messageToEmployee.setFrom(fromEmail);
					messageToEmployee.setTo(createLeaveRequest.getEmail());
					messageToEmployee.setSubject(emailSubject);
					messageToEmployee.setText("Request for leave");
					mailSender.send(messageToEmployee);
					System.out.println(messageToEmployee);
				}catch (RuntimeException e)
				{
					e.printStackTrace();
				}
			}
			else if (createLeaveRequestDto.getStatus().equals("2"))
			{
				try {
					SimpleMailMessage messageToEmployee = new SimpleMailMessage();
					messageToEmployee.setFrom(fromEmail);
					messageToEmployee.setTo(createLeaveRequest.getEmail());
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
				return null;
			}
		}
		else {
			throw new RuntimeException("Create Leave Not Found for Id : "+id);
		}
		return createLeaveRequestDto;
	}

	public CreateLeaveRequest createLeaveRequestdtotoCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto) {
		CreateLeaveRequest createLeaveRequest=this.modelMapper.map(createLeaveRequestDto,CreateLeaveRequest.class);
//		createLeaveRequest.setStatus("3");
		return createLeaveRequest;
	}

	public CreateLeaveRequestDto createLeaveRequesttoCreateLeaveRequestdto(CreateLeaveRequest createLeaveRequest)
	{
		CreateLeaveRequestDto createLeaveRequestDto=this.modelMapper.map(createLeaveRequest,CreateLeaveRequestDto.class);
		return createLeaveRequestDto;
	}

}
