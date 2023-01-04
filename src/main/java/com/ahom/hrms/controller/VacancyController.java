package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.VacancyDto;
import com.ahom.hrms.serviceimpl.VacancyServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/vancancies")
public class VacancyController {

	@Autowired
	VacancyServiceImpl vacancyService;
	
	@PostMapping("/vacancy")
	
	public ResponseEntity<VacancyDto> dataSave(@RequestBody VacancyDto vacancyDto){
		vacancyService.vacancySave(vacancyDto);
		return new ResponseEntity<>(vacancyDto,HttpStatus.CREATED);
		
	}
}
