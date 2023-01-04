package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.CreateLeaveRequestRepository;
import com.ahom.hrms.entities.CreateLeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.CreateLeaveRequestDto;

import com.ahom.hrms.service.CreateLeaveRequestService;

@Service
public class CreateLeaveRequestServiceImpl implements CreateLeaveRequestService{
	
	@Autowired
	CreateLeaveRequestRepository createLeaveRequestRepository;
	
	
	@Override
	 public void saveCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto)
	    {
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
	  public CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto)
	    {
	     createLeaveRequestRepository.save(createLeaveRequestdtotoCreateLeaveRequest(createLeaveRequestDto));
	    return createLeaveRequestDto;

	    }

	
	  
	  public CreateLeaveRequest createLeaveRequestdtotoCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto)
	    {
	        CreateLeaveRequest createLeaveRequest=new CreateLeaveRequest();

	        createLeaveRequest.setAvailableBalance(createLeaveRequestDto.getAvailableBalance());
			createLeaveRequest.setId(createLeaveRequestDto.getId());
	        createLeaveRequest.setDays(createLeaveRequestDto.getDays());
	        createLeaveRequest.setEndDate(createLeaveRequestDto.getEndDate());
	        createLeaveRequest.setLeaveApprover(createLeaveRequestDto.getLeaveApprover());
	        createLeaveRequest.setLeaveFor(createLeaveRequestDto.getLeaveFor());
	        createLeaveRequest.setLeaveType(createLeaveRequestDto.getLeaveType());
	        createLeaveRequest.setReasonForLeave(createLeaveRequestDto.getReasonForLeave());
	        createLeaveRequest.setSelectEmployee(createLeaveRequestDto.getSelectEmployee());
	        createLeaveRequest.setStartDate(createLeaveRequestDto.getStartDate());


	        return createLeaveRequest;
	    }
	  
	  public CreateLeaveRequestDto createLeaveRequesttoCreateLeaveRequestdto(CreateLeaveRequest createLeaveRequest) {
		  CreateLeaveRequestDto createLeaveRequestDto= new CreateLeaveRequestDto();
		  
		  createLeaveRequestDto.setAvailableBalance(createLeaveRequest.getAvailableBalance());
		  createLeaveRequestDto.setDays(createLeaveRequest.getDays());
		  createLeaveRequestDto.setEndDate(createLeaveRequest.getEndDate());
		  createLeaveRequestDto.setLeaveApprover(createLeaveRequest.getLeaveApprover());
		  createLeaveRequestDto.setLeaveFor(createLeaveRequest.getLeaveFor());
		  createLeaveRequestDto.setLeaveType(createLeaveRequest.getLeaveType());
		  createLeaveRequestDto.setReasonForLeave(createLeaveRequest.getReasonForLeave());
		  createLeaveRequestDto.setSelectEmployee(createLeaveRequest.getSelectEmployee());
		  createLeaveRequestDto.setStartDate(createLeaveRequest.getStartDate());

		  
		  return createLeaveRequestDto;
		  
	  }

}
