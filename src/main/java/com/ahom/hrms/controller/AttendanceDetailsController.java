package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.AttendanceDetailsDto;
import com.ahom.hrms.serviceimpl.AttendanceDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/AttendanceDetails")
public class AttendanceDetailsController {
	
	@Autowired
	AttendanceDetailsServiceImpl attendanceDetailsService;

	 @PostMapping("/post")
	    public ResponseEntity<AttendanceDetailsDto> saveEmp(@RequestBody AttendanceDetailsDto attendanceDetailsDto)
	    {
	        attendanceDetailsService.saveAttendanceDetails(attendanceDetailsDto);

	        return new ResponseEntity<>(attendanceDetailsDto, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/get")
	    public ResponseEntity<List<AttendanceDetailsDto>> getAttendanceDetails()
	    {
	        List<AttendanceDetailsDto> allAttendanceDetails = attendanceDetailsService.getAllAttendanceDetails();

	        return ResponseEntity.of(Optional.of(allAttendanceDetails));

	    }
	 
//	  @DeleteMapping("/attendanceDetails/{attendanceDetailsId}")
//	    public void deleteEmp(@PathVariable("attendanceDetailsId") int id){
//	        attendanceDetailsService.deleteAttendanceDetails(id);
//	    }
	  
	  @PutMapping("/put")
	    public ResponseEntity<AttendanceDetailsDto> updateEmp(@RequestBody AttendanceDetailsDto attendanceDetailsDto)
	    {
	        attendanceDetailsService.updateAttendanceDetails(attendanceDetailsDto);
	        return new ResponseEntity<>(attendanceDetailsDto, HttpStatus.ACCEPTED);
	    }


}
