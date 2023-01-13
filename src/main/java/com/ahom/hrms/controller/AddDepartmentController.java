package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.AddDepartmentDto;
import com.ahom.hrms.service.AddDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")

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

	@GetMapping("/getall")
	public ResponseEntity<List<AddDepartmentDto>> getALlUser()
	{
		return new ResponseEntity<>(this.addDepartmentService.getALlUser(),HttpStatus.CREATED);
	}

}
