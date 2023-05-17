package com.ahom.hrms.controller;


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
	public ResponseEntity<EmployementTypeMasterDto> saveEmployement(@Valid @RequestBody EmployementTypeMasterDto employementTypeMasterDto){
		employementTypeMasterService.saveEmployement(employementTypeMasterDto);
		return new ResponseEntity<>(employementTypeMasterDto, HttpStatus.CREATED);
	}

//	rePush
	@GetMapping("/getAllEmp")
	public List<EmployementTypeMasterDto> getTypeMaster(){
		List<EmployementTypeMasterDto> allEmployementTypeMasterDto=employementTypeMasterService.getAll();
		return allEmployementTypeMasterDto;

	}

	@DeleteMapping("/employment/delete/{id}")
	public void deleteEmployement(@PathVariable int id)
	{
		employementTypeMasterService.deleteEMployement(id);
	}
}
