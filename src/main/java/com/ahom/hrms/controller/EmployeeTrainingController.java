package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.serviceimpl.TrainingToEmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.EmployeeTrainingDto;


@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeTrainingController {
	
	@Autowired
	TrainingToEmployeeServiceImpl trainingToEmployeeService;
	
	@PostMapping("/save")
	public ResponseEntity<EmployeeTrainingDto> saveEmployeeTraining(@RequestBody EmployeeTrainingDto employeeTrainingDto){
		trainingToEmployeeService.saveEmployee(employeeTrainingDto);
		return new ResponseEntity<>(employeeTrainingDto,HttpStatus.CREATED);
	}
	//fetch data from database
	
	@GetMapping("/get")
	public List<EmployeeTrainingDto> getEmployeeTraining(){
		
		List<EmployeeTrainingDto> allEmployeeTrainingDtos = trainingToEmployeeService.getAll();
		return allEmployeeTrainingDtos;
	}
	
	//Update data from database By Id
	
	@PutMapping("/edit/{Id}")
	public EmployeeTrainingDto updateall(@RequestBody EmployeeTrainingDto employeeTrainingDto,@PathVariable("Id") int id){
		this.trainingToEmployeeService.updateEmployeeTraining(employeeTrainingDto,id);
		return employeeTrainingDto;	}
	
	//Delete data from database
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id")int id) {

		trainingToEmployeeService.deleteEmployeeTraining(id);
	}

}
