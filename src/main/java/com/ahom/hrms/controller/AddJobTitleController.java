package com.ahom.hrms.controller;

import java.util.List;

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

import com.ahom.hrms.dto.AddJobTitleDto;
import com.ahom.hrms.serviceimpl.AddJobTitleServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/addjobtitle")
public class AddJobTitleController {

	@Autowired
	AddJobTitleServiceImpl addJobTitleService;
	
	@PostMapping("/save")
	
	public ResponseEntity<AddJobTitleDto>add(@RequestBody AddJobTitleDto addJobTitleDto)
	{
		addJobTitleService.saveTitle(addJobTitleDto);
		return new ResponseEntity<>(addJobTitleDto,HttpStatus.CREATED);
		
	}
	@GetMapping("/getjob")
	public List<AddJobTitleDto>get()
	{
		List<AddJobTitleDto>getDto=this.addJobTitleService.getJob();
		return getDto;
	}
	@GetMapping("/{getid}")
	public List<AddJobTitleDto>add(@PathVariable("getid")int id)
	{
		return addJobTitleService.getById(id);
	}
}

