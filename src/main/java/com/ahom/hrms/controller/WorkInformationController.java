package com.ahom.hrms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.service.WorkInformationService;

import java.util.List;


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


	///
	@GetMapping("/fetchdata")
	public List<WorkInformationDto> getWorkInformation(){
		List<WorkInformationDto> allWorkInformationDto=workInformationService.getAll();
		return allWorkInformationDto;

	}


}
