package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.LeaveTypeDto;

public interface LeaveTypeService {

	void SaveLeaveTypeDetail(LeaveTypeDto leaveTypeDto);

	List<LeaveTypeDto> getAllLeaveDetail();

	void deleteLaeveDetail(int i);

}
