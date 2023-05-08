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
	public List <Attendance> status( String name, String userName,String month) {

		List<Attendance> attendance1 = attendanceRpository.findBySelectEmployeeAndUserName(name,userName);
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
					findBySelectEmployeeAndDateBetween(name,startDate, endDate);

			filterAttendance = new ArrayList<>();

			for (Attendance attendance : byEmployeeName) {
					if (attendance.getStatus().equals("Present") || attendance.getStatus().equals("WFH")) {
						filterAttendance.add(attendance);
						long count = filterAttendance.size();
						System.out.println(filterAttendance.size());
					}
					}
				}
		return filterAttendance;
	}


	/** ------------- Using DTO Class in AttendanceDtoToAttendance --------------------------*/
	
	public Attendance AttendanceDtoToAttendance(AttendanceDto attendancedto) {
		UserMaster userMaster = userMasterRepository.findByUserName(attendancedto.getUserName());

		if (userMaster != null) {
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

		 public Integer countAttendance(String month, String name,String userName){
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
				 List<Attendance> attendance =  attendanceRpository.findBySelectEmployeeAndUserName(name,userName);
				 ArrayList<Attendance> list=new ArrayList<>();

				 if (attendance!=null){
					 for (Attendance attendance1:attendance) {
						 if (attendance1.getStatus().equals("Present")||attendance1.getStatus().equals("WFH"))
						 {
							 list.add(attendance1);
							 System.out.println(list);
							 System.out.println(list.size());
							 return list.size();
//							 return attendanceRpository.getOneSelectEmployee(startDate, endDate, name);
						 }

					 }

					 }
				 else {
					 throw new CustomException("name not correct");
				 }

			 return null;
		 }

		 }

