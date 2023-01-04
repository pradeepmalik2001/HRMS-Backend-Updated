package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.InterviewDto;
import com.ahom.hrms.serviceimpl.InterviewServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/intreview")
public class InterviewController {
	@Autowired
	InterviewServiceImpl interviewService;
	
	@PostMapping("/save")
	public ResponseEntity<InterviewDto>interSave(@RequestBody InterviewDto dto)
	{
		interviewService.saveInt(dto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
		
	}

}
