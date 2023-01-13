package com.ahom.hrms.controller;

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

	@PostMapping("/save")
	public ResponseEntity<AttendanceDto> saveEmp(@RequestBody AttendanceDto attendancedto) {
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
		attendanceService.updateEmployeeAttendance(attendancedto);

		return new ResponseEntity<> (attendancedto,HttpStatus.OK);
	}
	@GetMapping("/bydate")
	@ResponseBody
	public ResponseEntity<List<Attendance>> ot(@RequestParam String startdate,
											 @RequestParam String enddate,
											 @RequestParam String name)
	{
		List<Attendance> Emplfetch = attendanceService.gteOt(startdate,enddate,name);
		return new ResponseEntity<>(Emplfetch ,HttpStatus.OK);
	}
	@GetMapping("/status")
	public ResponseEntity<List<Attendance>>statusOfAttendance(@RequestParam String startdate,
															  @RequestParam String enddate,
															  @RequestParam String name,
															  @RequestParam String status)
	{
		List<Attendance>statusFetch=attendanceService.getByStatus(startdate,enddate,name,status);
		return new ResponseEntity<>(statusFetch,HttpStatus.ACCEPTED);	
	}


}
