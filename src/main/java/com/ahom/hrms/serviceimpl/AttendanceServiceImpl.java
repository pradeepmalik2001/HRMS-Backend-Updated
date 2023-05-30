package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Helper.Excel;
import com.ahom.hrms.Repository.AttendanceRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.dto.AttendanceDto;
import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.Employee;
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
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Object saveEmplAttendance(AttendanceDto attendancedto) {
		attendanceRpository.save(AttendanceDtoToAttendance(attendancedto));
		return attendancedto;
	}

	@Override
	public List<AttendanceDto> getAllEmplAttendance() {
		List<AttendanceDto> ListEmplAttendance = new ArrayList<AttendanceDto>();
		attendanceRpository.findAll().forEach(l1 -> ListEmplAttendance.add(AttendanceToAttendanceDto(l1)));
		return ListEmplAttendance;
	}
	@Override
	public Object deleteAttendance(int empId) {
		Attendance attendance=attendanceRpository.findById(empId).orElse(null);
		if (attendance!=null){
		attendanceRpository.deleteById(empId);
		return attendance;
		}
		else
			throw new RuntimeException("No Record Present for Id:"+empId);
	}
	@Override
	public AttendanceDto updateEmployeeAttendance(AttendanceDto attendancedto) {
		attendanceRpository.save(AttendanceDtoToAttendance(attendancedto));
		return attendancedto;
	}

	@Override
	public Object saveExcel(MultipartFile file) {

		try {
			String originalFilename = file.getOriginalFilename();

			List<Attendance> attendances = Excel.convertExcelToList(file.getInputStream(), originalFilename);
			this.attendanceRpository.saveAll(attendances);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Attendance> getAll() {
		return this.attendanceRpository.findAll();
	}

	@Override
	public List<Attendance> gteOt(Date startdate, Date enddate, String name) {
		List<Attendance> attendances=attendanceRpository.findBySelectEmployee(name);
		if (attendances.isEmpty()) {
			throw new RuntimeException("Record For Employee" +" "+ name + " " + "is not defined");

		}else {
			List<Attendance> list = attendanceRpository.findByNameAndDateRange(startdate, enddate, name);
			return new ArrayList<>(list);
		}

	}

	@Override
	public List<Attendance> getByStatus(Date startdate, Date enddate, String name,String status) {
		List<Attendance> attendances=attendanceRpository.findBySelectEmployee(name);
		if (!attendances.isEmpty()) {
			List<Attendance> list = attendanceRpository.findByMonth(startdate, enddate, name, status);
			System.out.println(list);
			List<Attendance> filterAttendance = new ArrayList<>();

			for (Attendance attendance : list) {
				filterAttendance.add(attendance);

				System.out.println(attendance);

			}
			return filterAttendance;
		}else
			throw new RuntimeException("Record For Employee" +" "+ name + " " + "is not defined");
	}

	@Override
	public List <Attendance> status( String name, String userName,String month) {

		List<Attendance> attendance1 = attendanceRpository.findByUserName(userName);
		List<Attendance>attendances=attendanceRpository.findBySelectEmployee(name);
		ArrayList<Attendance> filterAttendance = null;
		if (!attendance1.isEmpty()&& !attendances.isEmpty()) {

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
						System.out.println(filterAttendance.size());
					}
					}
			return filterAttendance;

				}else
					throw new RuntimeException("Record For Employee either" +" "+ userName + " " + "OR " +name+ " " + "is not defined");



	}


	/** ------------- Using DTO Class in AttendanceDtoToAttendance --------------------------*/
	
	public Attendance AttendanceDtoToAttendance(AttendanceDto attendancedto) {
		Optional<Employee> employee = employeeRepository.findByUserName(attendancedto.getUserName());

		if (employee.isPresent()) {
			return this.modelMapper.map(attendancedto, Attendance.class);
		}
		else {
			throw new RuntimeException("No employee found for UserName:"+ attendancedto.getUserName());
		}
	}

	/** ------------ Using DTO Class in AttendanceToAttendanceDto --------------------------*/
	
	 public AttendanceDto AttendanceToAttendanceDto(Attendance attendance)
	    {
			return this.modelMapper.map(attendance , AttendanceDto.class);
	             }

		 public Integer countAttendance(String month,String userName){
			 List<Attendance> attendance1 = attendanceRpository.findByUserName(userName);
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

				 List<Attendance> byEmployeeName = attendanceRpository.findByUserName(userName);

				 filterAttendance = new ArrayList<>();
				 if (byEmployeeName!=null) {
					 for (Attendance attendance : byEmployeeName) {
						 if (attendance.getStatus().equals("Present") || attendance.getStatus().equals("WFH")) {
							 filterAttendance.add(attendance);
							 long count = filterAttendance.size();
							 System.out.println(filterAttendance.size());
						 }
					 }
				 }return filterAttendance.size();
			 }
			 return null;
		 }
		 }

