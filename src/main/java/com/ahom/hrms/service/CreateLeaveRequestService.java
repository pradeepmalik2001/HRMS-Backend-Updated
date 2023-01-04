package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.CreateLeaveRequestDto;

public interface CreateLeaveRequestService {

	void saveCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto);

	List<CreateLeaveRequestDto> getAllCreateLeaveRequest();

	CreateLeaveRequestDto updateCreateLeaveRequest(CreateLeaveRequestDto createLeaveRequestDto);
	
	

}
