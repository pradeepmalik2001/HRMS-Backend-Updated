package com.ahom.hrms.controller;

import com.ahom.hrms.Repository.AllowancesRepository;
import com.ahom.hrms.dto.AllowancesDto;
import com.ahom.hrms.serviceimpl.AllowancesServicesimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/allowance")
@CrossOrigin
public class AllowancesController { 
	@Autowired
    AllowancesServicesimpl allowancesServices;
	@Autowired
    AllowancesRepository allowancesRepository;
	
	@PostMapping("/allowances")
	public ResponseEntity<AllowancesDto> saveAllowanc(@RequestBody @Valid AllowancesDto allowancesDto){
		allowancesServices.saveAllowances(allowancesDto);		
		return new ResponseEntity<>(allowancesDto,HttpStatus.CREATED);
		}
	
	@DeleteMapping("/allowance/{id}")
	public void  deleteAllowances(@PathVariable("id")int id) {
		
		allowancesServices.deleteAllowances(id);
	}
	
	
	@PutMapping("/allowance")
	public ResponseEntity<AllowancesDto>updateall(@RequestBody AllowancesDto allowancesDto){
		allowancesServices.updateAllowances(allowancesDto);
		return new ResponseEntity<>(allowancesDto,HttpStatus.CREATED);
		
	}
	@GetMapping("/allowances")
	public List<AllowancesDto> getAllAllowances()
	{
		List<AllowancesDto> allAllowances = allowancesServices.getAllAllowances();
		return allAllowances ;
	}


}
