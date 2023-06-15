package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.serviceimpl.TrainingToEmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.EmployeeTrainingDto;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeTrainingController {
	
	@Autowired
	TrainingToEmployeeServiceImpl trainingToEmployeeService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveEmployeeTraining(@Valid @RequestBody EmployeeTrainingDto employeeTrainingDto){
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,trainingToEmployeeService.saveEmployee(employeeTrainingDto));
	}
	//fetch data from database
	
	@GetMapping("/get")
	public ResponseEntity<Object> getEmployeeTraining(){
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,trainingToEmployeeService.getAll());
	}
	
	//Update data from database By Id
	
	@PutMapping("/edit/{Id}")
	public EmployeeTrainingDto updateall(@RequestBody EmployeeTrainingDto employeeTrainingDto,@PathVariable("Id") String id){
		this.trainingToEmployeeService.updateEmployeeTraining(employeeTrainingDto,id);
		return employeeTrainingDto;	}
	
	//Delete data from database
	
	@DeleteMapping("/delete/{id}")
	public Object delete(@PathVariable("id")String id) {

		return ResponseHandler.responseBuilder("Data Deleted Successfully",HttpStatus.OK,trainingToEmployeeService.deleteEmployeeTraining(id));
	}

}
