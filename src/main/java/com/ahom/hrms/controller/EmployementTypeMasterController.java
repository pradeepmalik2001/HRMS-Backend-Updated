package com.ahom.hrms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.EmployementTypeMasterDto;
import com.ahom.hrms.service.EmployementTypeMasterService;

import java.util.List;

@RestController
@CrossOrigin
public class EmployementTypeMasterController {

	@Autowired
	EmployementTypeMasterService employementTypeMasterService;

	//save data
	@PostMapping("/saveemployement")
	public ResponseEntity<EmployementTypeMasterDto> saveEmployement(@RequestBody EmployementTypeMasterDto employementTypeMasterDto){
		employementTypeMasterService.saveEmployement(employementTypeMasterDto);
		return new ResponseEntity<>(employementTypeMasterDto, HttpStatus.CREATED);
	}

//	rePush
	@GetMapping("/getallEmp")
	public List<EmployementTypeMasterDto> getTypeMaster(){
		List<EmployementTypeMasterDto> allEmployementTypeMasterDto=employementTypeMasterService.getAll();
		return allEmployementTypeMasterDto;

	}

}
