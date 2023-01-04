package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AttendanceDto;

public interface AttendanceService {

	public void saveEmplAttendance(AttendanceDto attendancedto);
	
	public List<AttendanceDto> getAllEmplAttendance();
	
	public void deleteAttendance(int empId);
	public void updateEmployeeAttendance(AttendanceDto attendancedto);
	
}
