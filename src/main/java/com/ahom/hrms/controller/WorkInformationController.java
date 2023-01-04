package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.service.WorkInformationService;

@RestController
@CrossOrigin
public class WorkInformationController {

	@Autowired
	WorkInformationService workInformationService;

	//save data
	@PostMapping("/savework")
	public ResponseEntity<WorkInformationDto> saveWork(@RequestBody WorkInformationDto workInformationDto){
		workInformationService.saveWorkInfo(workInformationDto);
		return new ResponseEntity<>(workInformationDto, HttpStatus.CREATED);
	}

}
