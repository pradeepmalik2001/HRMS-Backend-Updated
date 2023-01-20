package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AttendanceDetailsDto;
import com.ahom.hrms.entities.AttendanceDetails;


public interface AttendanceDetailsService {

	void saveAttendanceDetails(AttendanceDetailsDto attendanceDetailsDto);

	List<AttendanceDetailsDto> getAllAttendanceDetails();

	AttendanceDetails updateAttendanceDetails(AttendanceDetails attendanceDetails,int id);


}
