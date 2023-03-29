package com.ahom.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ahom.hrms.Helper.Excel;
import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.OverTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.AttendanceDto;
import com.ahom.hrms.serviceimpl.AttendanceServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

	@Autowired
	AttendanceServiceImpl attendanceService;

//	@Autowired
//	AttendanceServiceImpl attendanceServiceimpl;

	@PostMapping("/save")
	public ResponseEntity<AttendanceDto> saveEmp(@RequestBody AttendanceDto attendancedto) {
		attendancedto.setDate(new Date());
		attendanceService.saveEmplAttendance(attendancedto);
		 return new ResponseEntity<>(attendancedto ,HttpStatus.CREATED);
	}
	@PostMapping("/upload")
	public ResponseEntity<?>save(@RequestParam("file")MultipartFile file){
		if (Excel.checkFormat(file))
		{
			attendanceService.saveExcel(file);
			String originalFilename = file.getOriginalFilename();
			return  ResponseEntity.ok(Map.of("message","file uploaded successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file");
	}

	@GetMapping("/fetch")
	public ResponseEntity<List<AttendanceDto>> getEmplAttendance() {
		List<AttendanceDto> allEmployee = attendanceService.getAllEmplAttendance();
		return new ResponseEntity<>(allEmployee ,HttpStatus.OK);
	}

	@DeleteMapping("/Delete/{employeeId}")
	public void deleteEmp(@PathVariable("employeeId") int id) {
		attendanceService.deleteAttendance(id);
	}

	@PutMapping("/update")
	public ResponseEntity<AttendanceDto> updateEmplAttendance(@RequestBody AttendanceDto attendancedto) {
		attendancedto.setDate(new Date());
		attendanceService.updateEmployeeAttendance(attendancedto);

		return new ResponseEntity<> (attendancedto,HttpStatus.OK);
	}
	@PostMapping("/bydate")
	@ResponseBody
	public ResponseEntity<List<Attendance>> ot(@RequestParam String startdate,
											 @RequestParam String enddate,
											 @RequestParam String name) throws ParseException {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date stdate= format.parse(startdate);
		Date endate= format.parse(enddate);
		List<Attendance> Emplfetch = attendanceService.gteOt(stdate,endate,name);
		return new ResponseEntity<>(Emplfetch ,HttpStatus.OK);
	}
	@PostMapping("/status")
	public ResponseEntity<List<Attendance>>statusOfAttendance(@RequestParam String startdate,
															  @RequestParam String enddate,
															  @RequestParam String name,
															  @RequestParam String status) throws ParseException {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=format.parse(startdate);
		Date endDate=format.parse(enddate);
		List<Attendance>statusFetch=attendanceService.getByStatus(startDate,endDate,name,status);
		return new ResponseEntity<>(statusFetch,HttpStatus.ACCEPTED);
	}
	@PostMapping("/count")
	public ResponseEntity<Integer>countAttendance(@RequestParam String startdate,
															  @RequestParam String enddate,
															  @RequestParam String name,
															  @RequestParam String status) throws ParseException {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=format.parse(startdate);
		Date endDate=format.parse(enddate);
		Integer attendance = attendanceService.countAttendance(startDate, endDate, name, status);
		return new ResponseEntity<>(attendance,HttpStatus.ACCEPTED);
	}

}
