package com.ahom.hrms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.OverTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.OverTimeDto;
import com.ahom.hrms.serviceimpl.OverTimeServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/OverTime")
@CrossOrigin
public class OverTimeController {

	
	@Autowired
	OverTimeServiceImpl overTimeservice;
	
	@PostMapping("/save")
	public ResponseEntity<Object> EmplSave(@Valid @RequestBody OverTimeDto overtimedto)
	{
		overtimedto.setDate(new Date());
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,overTimeservice.EmployeeSave(overtimedto));
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<Object> Emplfetch()
	{
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,overTimeservice.Employeefetch());
	}

	@PostMapping("/byDate")
	@ResponseBody
	public ResponseEntity<Object> ot(@RequestParam(required = false,name = "month") String month,

												@RequestParam(required = false,name="userName") String userName) throws ParseException
	{
		List<OverTime> Emplfetch = overTimeservice.gteOt(month,userName);
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,Emplfetch);
	}

	@PostMapping("/byTime")
	@ResponseBody
	public ResponseEntity<Object> ott(@RequestParam(required = false,name = "month") String month,
									 @RequestParam(required = false,name="userName") String userName) throws ParseException
	{
           int count = overTimeservice.getByUserNameAndMonth(month, userName);
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,count);
	}



}
