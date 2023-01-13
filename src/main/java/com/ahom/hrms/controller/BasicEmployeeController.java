package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.service.BasicEmployeeService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/basic")
public class BasicEmployeeController {

	@Autowired
	BasicEmployeeService basicEmployeeService;

	//save data
	@PostMapping("/saveemployee")
	public ResponseEntity<BasicEmployeeDto> saveEmployees(@RequestBody BasicEmployeeDto basicEmployeeDto){
		basicEmployeeService.saveEmployee(basicEmployeeDto);
		return new ResponseEntity<>(basicEmployeeDto, HttpStatus.CREATED);
	}

	//fetch data by id
	@GetMapping("/fetchemployee/{employeeid}")
	public ResponseEntity<BasicEmployeeDto> getEmployee(@PathVariable("employeeid") Integer employeeId){
		BasicEmployeeDto basicEmployeeDto = basicEmployeeService.employeeById(employeeId);
		ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(basicEmployeeDto);
	}
	@GetMapping("/fetchdata")
	public ResponseEntity<List<BasicEmployeeDto>> getAll()
	{
		return new ResponseEntity<>(this.basicEmployeeService.getAllEmployee(),HttpStatus.ACCEPTED);
	}




}
