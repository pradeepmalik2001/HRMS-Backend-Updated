package com.ahom.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.OverTimeDto;
import com.ahom.hrms.serviceimpl.OverTimeServiceImpl;

@RestController
@RequestMapping("/OverTime")
@CrossOrigin
public class OverTimeController {

	
	@Autowired
	OverTimeServiceImpl overTimeservice;
	
	@PostMapping("/save")
	public ResponseEntity<OverTimeDto> EmplSave(@RequestBody OverTimeDto overtimedto)
	{
		 overTimeservice.EmployeeSave(overtimedto);	
 		 return new ResponseEntity<>(overtimedto ,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<List<OverTimeDto>> Emplfetch()
	{
		List<OverTimeDto> Emplfetch = overTimeservice.Employeefetch();
		return new ResponseEntity<>(Emplfetch ,HttpStatus.OK);
	}
	
//	OverTime overtime =new OverTime();
//	Duration duration = Duration.between(setStarttime(Temporal starttime),setEndtime(Temporal endtime));
//	@GetMapping("/viewbyId/{emplyeeId}")
//	public Optional<OverTime> getAttendanceById(@PathVariable("employeeId") int empId) {
//		return overTimeservice.viewOverTimeById(empId);
//	}



}
