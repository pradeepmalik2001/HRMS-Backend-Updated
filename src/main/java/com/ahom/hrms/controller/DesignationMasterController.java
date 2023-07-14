package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.DesignationMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.DesignationMasterDto;
import com.ahom.hrms.service.DesignationMasterService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/designation")
public class DesignationMasterController {

	@Autowired
	DesignationMasterService designationMasterService;

	//save data
	@PostMapping("/saveDesignation")
	public ResponseEntity<Object> saveDesignation(@Valid @RequestBody DesignationMasterDto designationMasterDto){

		return ResponseHandler.responseBuilder("Data Saved",HttpStatus.CREATED,designationMasterService.saveDesignation(designationMasterDto));
	}

	//fetch all data from database
	@GetMapping("/fetchAllDesignation")
	public ResponseEntity<Object> getDesignation(){
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,designationMasterService.getAllEmployee());
	}

	@DeleteMapping("/designation/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id){
		return ResponseHandler.responseBuilder("Designation for ID:" +id +" " +"deleted successfully",
				HttpStatus.OK, designationMasterService.deleteDesignation(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateDesignation(@Valid @RequestBody DesignationMaster designationMaster,@PathVariable int id)
	{
		return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,designationMasterService.updateDesignation(designationMaster, id));
	}
}
