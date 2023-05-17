package com.ahom.hrms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.service.WorkInformationService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/work")

@CrossOrigin
public class WorkInformationController {

	@Autowired
	WorkInformationService workInformationService;

	//save data
	@PostMapping("/saveWork")
	public ResponseEntity<WorkInformationDto> saveWork(@Valid @RequestBody WorkInformationDto workInformationDto) throws Exception{
		workInformationService.saveWorkInfo(workInformationDto);
		return new ResponseEntity<>(workInformationDto, HttpStatus.CREATED);
	}
	@GetMapping("/fetchData")
	public List<WorkInformationDto> getWorkInformation(){
		List<WorkInformationDto> allWorkInformationDto=workInformationService.getAll();
		return allWorkInformationDto;
	}
}