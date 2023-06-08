package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.entities.CreateLeaveRequest;

public interface CreateLeaveRequestService {

	CreateLeaveRequestDto saveCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto);

	List<CreateLeaveRequestDto> getAllCreateLeaveRequest();

	CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto,String id);
	
	

}
