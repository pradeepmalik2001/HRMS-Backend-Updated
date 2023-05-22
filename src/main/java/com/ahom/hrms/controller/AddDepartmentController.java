package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.AddDepartmentDto;
import com.ahom.hrms.service.AddDepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")

@CrossOrigin

public class AddDepartmentController {

	@Autowired
	AddDepartmentService addDepartmentService;

	//save data
	@PostMapping("/saveDepartment")
	public ResponseEntity<Object> saveDepartments(@Valid @RequestBody AddDepartmentDto addDepartmentDto) throws Exception {
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,addDepartmentService.saveDepartment(addDepartmentDto));
//		return new ResponseEntity<>(addDepartmentDto, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getALlUser()
	{
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,addDepartmentService.getALlUser());
//		return new ResponseEntity<>(this.addDepartmentService.getALlUser(),HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable ("id") int id){
		addDepartmentService.delete(id);
	}


	@GetMapping("/get/{departmentId}")
	public ResponseEntity<Object> getById(@PathVariable("departmentId") int departmentId)
	{
		return ResponseHandler.responseBuilder("Fetch Successfully",HttpStatus.OK,addDepartmentService.getDepartmentById(departmentId));
	}
}
