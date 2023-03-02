package com.ahom.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		overtimedto.setDate(new Date());
		 overTimeservice.EmployeeSave(overtimedto);	
 		 return new ResponseEntity<>(overtimedto ,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<List<OverTimeDto>> Emplfetch()
	{
		List<OverTimeDto> Emplfetch = overTimeservice.Employeefetch();
		return new ResponseEntity<>(Emplfetch ,HttpStatus.OK);
	}

	@PostMapping("/bydate")
	@ResponseBody
	public ResponseEntity<List<OverTime>> ot(@RequestParam String startdate,
												@RequestParam String enddate,
												@RequestParam String name) throws ParseException {
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date stdate= format.parse(startdate);
		Date endate= format.parse(enddate);
		List<OverTime> Emplfetch = overTimeservice.gteOt(stdate,endate,name);
		return new ResponseEntity<>(Emplfetch ,HttpStatus.OK);
	}
	
//	OverTime overtime =new OverTime();
//	Duration duration = Duration.between(setStarttime(Temporal starttime),setEndtime(Temporal endtime));
//	@GetMapping("/viewbyId/{emplyeeId}")
//	public Optional<OverTime> getAttendanceById(@PathVariable("employeeId") int empId) {
//		return overTimeservice.viewOverTimeById(empId);
//	}



}
