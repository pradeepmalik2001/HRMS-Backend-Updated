package com.ahom.hrms.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ahom.hrms.Helper.Excel;
import com.ahom.hrms.Repository.AttendanceRepository;
import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.OverTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.AttendanceDto;


import com.ahom.hrms.service.AttendanceService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRpository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public void saveEmplAttendance(AttendanceDto attendancedto) {
		attendanceRpository.save(AttendanceDtoToAttendance(attendancedto));
	}

	@Override
	public List<AttendanceDto> getAllEmplAttendance() {
		List<AttendanceDto> ListEmplAttendance = new ArrayList<AttendanceDto>();
		attendanceRpository.findAll().forEach(l1 -> ListEmplAttendance.add(AttendanceToAttendanceDto(l1)));
		return ListEmplAttendance;
	}
	@Override
	public void deleteAttendance(int empId) {
		attendanceRpository.deleteById(empId);
	}
	@Override
	public void updateEmployeeAttendance(AttendanceDto attendancedto) {
		attendanceRpository.save(AttendanceDtoToAttendance(attendancedto));
	}

	String originalFilename="";
	@Override
	public void saveExcel(MultipartFile file) {

		try {
			String originalFilename = file.getOriginalFilename();

			List<Attendance> attendances = Excel.convertExcelToList(file.getInputStream(), originalFilename);
			this.attendanceRpository.saveAll(attendances);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Attendance> getAll() {
		return this.attendanceRpository.findAll();
	}

	@Override
	public List<Attendance> gteOt(String startdate, String enddate, String name) {
		List<Attendance> list = attendanceRpository.findByNameAndDateRange(startdate, enddate, name);
		System.out.println(list);
		List<Attendance> filterAttendance = new ArrayList<>();

		for (Attendance attendance: list) {
			filterAttendance.add(attendance);

			System.out.println(attendance);

		}
		return filterAttendance;
	}

	@Override
	public List<Attendance> getByStatus(String startdate, String enddate, String name,String status) {
		List<Attendance> list = attendanceRpository.findByMonth(startdate, enddate, name,status);
		System.out.println(list);
		List<Attendance> filterAttendance = new ArrayList<>();

		for (Attendance attendance: list) {
			filterAttendance.add(attendance);

			System.out.println(attendance);

		}
		return filterAttendance;
	}


	/** ------------- Using DTO Class in AttendanceDtoToAttendance --------------------------*/
	
	public Attendance AttendanceDtoToAttendance(AttendanceDto attendancedto)
	{
		Attendance attendance =this.modelMapper.map(attendancedto , Attendance.class);
		
		return attendance;
	}

	/** ------------ Using DTO Class in AttendanceToAttendanceDto --------------------------*/
	
	 public AttendanceDto AttendanceToAttendanceDto(Attendance attendance)
	    {
		 AttendanceDto attendancedto= this.modelMapper.map(attendance , AttendanceDto.class);

	     
	        return attendancedto;
	    
         }
}
