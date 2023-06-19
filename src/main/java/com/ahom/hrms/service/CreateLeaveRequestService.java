package com.ahom.hrms.service;

import java.text.ParseException;
import java.util.List;

import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.entities.CreateLeaveRequest;

public interface CreateLeaveRequestService {

	CreateLeaveRequest saveCreateLeaveRequest(CreateLeaveRequest createLeaveRequest) throws ParseException;

	List<CreateLeaveRequestDto> getAllCreateLeaveRequest();

	CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto,String id);
	
	

}
