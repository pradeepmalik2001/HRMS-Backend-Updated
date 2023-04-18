package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.DesignationMasterDto;
import com.ahom.hrms.service.DesignationMasterService;

@RestController
@CrossOrigin
@RequestMapping("/designation")
public class DesignationMasterController {

	@Autowired
	DesignationMasterService designationMasterService;

	//save data
	@PostMapping("/savedesignation")
	public ResponseEntity<DesignationMasterDto> saveDesignation(@RequestBody DesignationMasterDto designationMasterDto){
		designationMasterService.saveDesignation(designationMasterDto);
		return new ResponseEntity<>(designationMasterDto, HttpStatus.CREATED);
	}

	//fetch all data from database
	@GetMapping("/fetchalldesignation")
	public ResponseEntity<List<DesignationMasterDto>> getDesignation(){
		List<DesignationMasterDto> allEmployee = designationMasterService.getAllEmployee();
		ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(allEmployee));
	}

}
