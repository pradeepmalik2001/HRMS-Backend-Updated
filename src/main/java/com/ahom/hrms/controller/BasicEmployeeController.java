package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.service.BasicEmployeeService;
import com.ahom.hrms.serviceimpl.ReportXmlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/basic")
public class BasicEmployeeController {

	@Autowired
	BasicEmployeeService basicEmployeeService;

	@PostMapping("/saveemployee")
	public ResponseEntity<Object> saveEmployees(@Valid @RequestBody BasicEmployeeDto basicEmployeeDto) throws ParseException {
		return ResponseHandler.responseBuilder("Employee Data saved Successfully",HttpStatus.OK,
				basicEmployeeService.saveEmployee(basicEmployeeDto));

	}


	@GetMapping("/fetchemployee/{employeeid}")
	public ResponseEntity<BasicEmployee> getEmployee(@PathVariable("employeeid") String employeeId){
		BasicEmployee basicEmployeeDto = basicEmployeeService.employeeById(employeeId);
		ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(basicEmployeeDto);
	}


	@GetMapping("/fetchdata")
	public ResponseEntity<Object> getAll()
	{
		return ResponseHandler.responseBuilder("Employees Data",HttpStatus.OK,
				basicEmployeeService.getAllEmployee());
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable String id)
	{
	return ResponseHandler.responseBuilder("Employee deleted successfully",HttpStatus.OK,
			basicEmployeeService.deleteEmployee(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object>update(@RequestBody BasicEmployeeDto employee,@PathVariable String id)
	{
		return ResponseHandler.responseBuilder("update success",HttpStatus.OK,
				basicEmployeeService.update(employee,id));
	}
}
