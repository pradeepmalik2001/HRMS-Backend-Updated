package com.ahom.hrms.controller;

import java.util.Date;
import java.util.List;

import com.ahom.hrms.entities.OverTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.OverTimeDto;
import com.ahom.hrms.serviceimpl.OverTimeServiceImpl;

@RestController
@RequestMapping("/OverTime")
@CrossOrigin
public class OverTimeController {

	
	@Autowired
	OverTimeServiceImpl overTimeservice;
	
	@PostMapping("/save")
	public ResponseEntity<OverTime> EmplSave(@RequestBody OverTime overtimedto)
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

	@GetMapping("/bydate")
	@ResponseBody
	public ResponseEntity<List<OverTime>> ot(@RequestParam String startdate,
												@RequestParam String enddate,
												@RequestParam String name)
	{
		List<OverTime> Emplfetch = overTimeservice.gteOt(startdate,enddate,name);
		return new ResponseEntity<>(Emplfetch ,HttpStatus.OK);
	}
	
//	OverTime overtime =new OverTime();
//	Duration duration = Duration.between(setStarttime(Temporal starttime),setEndtime(Temporal endtime));
//	@GetMapping("/viewbyId/{emplyeeId}")
//	public Optional<OverTime> getAttendanceById(@PathVariable("employeeId") int empId) {
//		return overTimeservice.viewOverTimeById(empId);
//	}



}
