package com.ahom.hrms.controller;
import com.ahom.hrms.Response.ResponseHandler;
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
	public ResponseEntity<Object> saveWork(@Valid @RequestBody WorkInformationDto workInformationDto) throws Exception{
		return ResponseHandler.responseBuilder("Work information of employee"+ " "+workInformationDto.getEmployeeName(),
				HttpStatus.OK,workInformationService.saveWorkInfo(workInformationDto));
	}
	@GetMapping("/fetchData")
	public List<WorkInformationDto> getWorkInformation(){
		return workInformationService.getAll();
	}
}