package com.ahom.hrms.controller;

import com.ahom.hrms.Repository.AddDepartmentRepository;
import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.AddDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.AddDepartmentDto;
import com.ahom.hrms.service.AddDepartmentService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/department")

@CrossOrigin

public class AddDepartmentController {

	@Autowired
	AddDepartmentService addDepartmentService;

	@Autowired
	AddDepartmentRepository addDepartmentRepository;

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
	public ResponseEntity<Object> delete(@PathVariable ("id") int id) {
		Optional<AddDepartment> addDepartment = addDepartmentRepository.findById(id);
		if (addDepartment!=null)
		{
			return ResponseHandler.responseBuilder("Deleted Successfully", HttpStatus.OK, addDepartmentService.delete(id));
		}
		else {
			return ResponseHandler.responseBuilder("Deleted Successfully", HttpStatus.OK, addDepartmentService);
		}
	}


	@GetMapping("/get/{departmentId}")
	public ResponseEntity<Object> getById(@PathVariable("departmentId") int departmentId)
	{
		return ResponseHandler.responseBuilder("Fetch Successfully",HttpStatus.OK,addDepartmentService.getDepartmentById(departmentId));
	}
}
