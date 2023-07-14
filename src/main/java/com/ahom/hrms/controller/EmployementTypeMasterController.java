package com.ahom.hrms.controller;


import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.EmployementTypeMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.EmployementTypeMasterDto;
import com.ahom.hrms.service.EmployementTypeMasterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
	@RequestMapping("employment")
public class EmployementTypeMasterController {

	@Autowired
	EmployementTypeMasterService employementTypeMasterService;

	//save data
	@PostMapping("/saveEmployment")
	public ResponseEntity<Object> saveEmployement(@Valid @RequestBody EmployementTypeMasterDto employementTypeMasterDto){
		return ResponseHandler.responseBuilder("Data Saved",HttpStatus.OK,employementTypeMasterService.saveEmployement(employementTypeMasterDto));
	}

//	rePush
	@GetMapping("/getAllEmp")
	public ResponseEntity<Object> getTypeMaster(){
//		List<EmployementTypeMasterDto> allEmployementTypeMasterDto=employementTypeMasterService.getAll();
		return ResponseHandler.responseBuilder("Fetched Successfully",HttpStatus.OK,employementTypeMasterService.getAll());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployement(@PathVariable int id)
	{
		return ResponseHandler.responseBuilder("Deleted Successfully",HttpStatus.OK,employementTypeMasterService.deleteEMployement(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateEmployment(@Valid @RequestBody EmployementTypeMaster employementTypeMaster,@PathVariable int id)
	{
		return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,employementTypeMasterService.updateEmployement(employementTypeMaster, id));
	}
}
