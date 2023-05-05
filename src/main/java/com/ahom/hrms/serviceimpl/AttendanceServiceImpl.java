package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Helper.Excel;
import com.ahom.hrms.Repository.AttendanceRepository;
import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.dto.AttendanceDto;
import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.UserMaster;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRpository;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	private UserMasterRepository userMasterRepository;

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
	public List <Attendance> status( String name, String username,String status,String month) {

		Attendance attendance1 = attendanceRpository.findBySelectEmployee(name);
		ArrayList<Attendance> filterAttendance = null;
		if (attendance1 != null) {
			DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
			Date startDate, endDate;
			try {
				month = month.toUpperCase();
				startDate = dateFormat.parse(month + " " + Calendar.getInstance().get(Calendar.YEAR));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDate);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate = calendar.getTime();
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}

			List<Attendance> byEmployeeName = attendanceRpository.
					findBySelectEmployeeAndStatusAndDateBetween(name, status, startDate, endDate);

			filterAttendance = new ArrayList<>();

			for (Attendance attendance : byEmployeeName) {
				if (Objects.equals(username, attendance.getUserMaster().getUserName())) {
					if (attendance.getStatus().equals(status) || attendance.getStatus().equals("WFH")) {
						filterAttendance.add(attendance);
						long count = filterAttendance.size();
						System.out.println(filterAttendance.size());
					} else {
						throw new CustomException("dsdsa");
					}
				}
			}
		}
		return filterAttendance;
	}

	/** ------------- Using DTO Class in AttendanceDtoToAttendance --------------------------*/
	
	public Attendance AttendanceDtoToAttendance(AttendanceDto attendancedto) {
		UserMaster userMaster = userMasterRepository.findById(attendancedto.getId()).orElse(null);

		if (userMaster != null) {
			attendancedto.setUserMaster(userMaster);
			return this.modelMapper.map(attendancedto, Attendance.class);
		}
		else {
			throw new CustomException("No user Found");
		}
	}

	/** ------------ Using DTO Class in AttendanceToAttendanceDto --------------------------*/
	
	 public AttendanceDto AttendanceToAttendanceDto(Attendance attendance)
	    {
			return this.modelMapper.map(attendance , AttendanceDto.class);
	             }

		 public Integer countAttendance(String month, String name,String status,String username){
			 DateFormat dateFormat=new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
			 Date startDate,endDate;
				 try {
					 month = month.toUpperCase();
					 startDate = dateFormat.parse(month + " " + Calendar.getInstance().get(Calendar.YEAR));
					 Calendar calendar = Calendar.getInstance();
					 calendar.setTime(startDate);
					 calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
					 endDate = calendar.getTime();
				 } catch (ParseException e) {
					 throw new RuntimeException(e);
				 }
				 Attendance attendance
						 =attendanceRpository.findBySelectEmployee(name);

				 if (attendance!=null){
					 if (Objects.equals(username, attendance.getUserMaster().getUserName())) {

						 return attendanceRpository.getOneSelectEmployee(startDate, endDate, name, status);
					 }
					 else {
						 throw new CustomException("username not correct");
					 }
				 }
				 throw new CustomException("user not matched");
		 }





}
