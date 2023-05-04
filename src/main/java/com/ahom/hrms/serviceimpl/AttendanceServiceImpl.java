package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Helper.Excel;
import com.ahom.hrms.Repository.AttendanceRepository;
import com.ahom.hrms.dto.AttendanceDto;
import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<Attendance> gteOt(Date startdate, Date enddate, String name) {
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
	public List<Attendance> getByStatus(Date startdate, Date enddate, String name,String status) {
		List<Attendance> list = attendanceRpository.findByMonth(startdate, enddate, name,status);
		System.out.println(list);
		List<Attendance> filterAttendance = new ArrayList<>();

		for (Attendance attendance: list) {
			filterAttendance.add(attendance);

			System.out.println(attendance);

		}
		return filterAttendance;
	}

	@Override
	public List <Attendance> status(String name, String status,Date date)  {

	List<Attendance> byEmployeeName = attendanceRpository.findBySelectEmployeeAndStatusAndDate
			(name,status,date);
		LocalDate localDate=LocalDate.now();
		int year = localDate.getYear();
		System.out.println(year);
		int monthValue = localDate.getMonthValue();
		System.out.println(monthValue);
		int lengthOfMonth = localDate.lengthOfMonth();
		System.out.println(lengthOfMonth);

		ArrayList <Attendance> filterAttendance=new ArrayList<>();
		for (Attendance attendance:byEmployeeName) {
			if (attendance.getStatus().equals(status)){
				filterAttendance.add(attendance);
				System.out.println(filterAttendance.size());
			}

		}
		return filterAttendance ;
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


			return this.modelMapper.map(attendance , AttendanceDto.class);
	    
         }

		 public Integer countAttendance(Date startdate, Date enddate, String name,String status){
			 return attendanceRpository.getOneSelectEmployee(startdate, enddate, name, status);
	 }





}
