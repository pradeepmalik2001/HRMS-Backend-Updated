package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

import com.ahom.hrms.Repository.DesignationMasterRepository;
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

	@Autowired
	DesignationMasterRepository designationMasterRepository;
	//save data
	@PostMapping("/saveDesignation")
	public ResponseEntity<Object> saveDesignation(@Valid @RequestBody DesignationMasterDto designationMasterDto){

		return ResponseHandler.responseBuilder("Data Saved",HttpStatus.CREATED,designationMasterService.saveDesignation(designationMasterDto));
	}

	//fetch all data from database
	@GetMapping("/fetchAllDesignation")
	public ResponseEntity<List<DesignationMasterDto>> getDesignation(){
		List<DesignationMasterDto> allEmployee = designationMasterService.getAllEmployee();
		ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(allEmployee));
	}

	@DeleteMapping("/designation/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id){
		DesignationMaster designationMaster= designationMasterRepository.findById(id).orElse(null);
		return ResponseHandler.responseBuilder("Designation for ID:" +id +" " +"deleted successfully",
				HttpStatus.OK, designationMasterService.deleteDesignation(id));
	}
}
