package com.ahom.hrms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.AddJobTitleDto;
import com.ahom.hrms.serviceimpl.AddJobTitleServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/addJobTitle")
public class AddJobTitleController {

	@Autowired
	AddJobTitleServiceImpl addJobTitleService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveTitle(@Valid @RequestBody AddJobTitleDto addJobTitleDto)
	{
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.CREATED,addJobTitleService.saveTitle(addJobTitleDto));
	}

	@GetMapping("/getJob")
	public ResponseEntity<Object> get()
	{
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,addJobTitleService.getJob());
	}

	@GetMapping("/{getId}")
	public ResponseEntity<Object> add(@PathVariable("getId")int id)
	{
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,addJobTitleService.getById(id));
	}
}

