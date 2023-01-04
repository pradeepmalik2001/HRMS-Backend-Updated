package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AttendanceDetailsDto;


public interface AttendanceDetailsService {

	void saveAttendanceDetails(AttendanceDetailsDto attendanceDetailsDto);

	List<AttendanceDetailsDto> getAllAttendanceDetails();

	AttendanceDetailsDto updateAttendanceDetails(AttendanceDetailsDto attendanceDetailsDto);


}
