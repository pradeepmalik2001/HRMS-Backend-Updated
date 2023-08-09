package com.ahom.hrms.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ahom.hrms.dto.AttendanceDto;
import com.ahom.hrms.entities.Attendance;
import org.springframework.web.multipart.MultipartFile;

public interface AttendanceService {

	public Object saveEmplAttendance(AttendanceDto attendancedto);
	
	public List<AttendanceDto> getAllEmplAttendance();
	
	public Object deleteAttendance(int empId);
	public AttendanceDto updateEmployeeAttendance(AttendanceDto attendancedto);
	public Object saveExcel(MultipartFile file);
	public List<Attendance>getAll();
	public List<Attendance> gteOt(Date startdate
			, Date enddate
			, String name);
	public List<Attendance>getByStatus(Date startdate,
									   Date enddate,String name,
									   String status);

	public List <Attendance> status(String name, String userName,String month);

	public double countAttendance(String name, String userName,String month,String status);

	List<Attendance> dataFor(String name, String userName,String month) throws ParseException;

}
