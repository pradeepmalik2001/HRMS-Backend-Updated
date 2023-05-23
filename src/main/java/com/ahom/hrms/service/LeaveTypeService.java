package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.LeaveTypeDto;
import com.ahom.hrms.entities.LeaveType;

public interface LeaveTypeService {

	LeaveTypeDto SaveLeaveTypeDetail(LeaveTypeDto leaveTypeDto);

	List<LeaveTypeDto> getAllLeaveDetail();

	LeaveType deleteLaeveDetail(int id);

	LeaveType updateLeaveType(LeaveTypeDto leaveType,int id);

}
