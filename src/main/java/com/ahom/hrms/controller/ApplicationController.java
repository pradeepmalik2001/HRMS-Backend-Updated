package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.ApplicationDto;
import com.ahom.hrms.serviceimpl.ApplicationServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	ApplicationServiceImpl applicationService;
	
	@PostMapping("/save")
	public ResponseEntity<ApplicationDto> saveData(@RequestBody ApplicationDto applicationDto)
	{
		applicationService.svaeApp(applicationDto);
		return new ResponseEntity<>(applicationDto,HttpStatus.CREATED);
	}
}
