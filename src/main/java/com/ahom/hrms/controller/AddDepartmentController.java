package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.AddDepartmentDto;
import com.ahom.hrms.service.AddDepartmentService;

@RestController
@CrossOrigin
public class AddDepartmentController {

	@Autowired
	AddDepartmentService addDepartmentService;

	//save data
	@PostMapping("/savedepartment")
	public ResponseEntity<AddDepartmentDto> saveDepartments(@RequestBody AddDepartmentDto addDepartmentDto){
		addDepartmentService.saveDepartment(addDepartmentDto);
		return new ResponseEntity<>(addDepartmentDto, HttpStatus.CREATED);
	}

}
