package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.service.EmergencyContactInfoService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/emergency")
public class EmergencyContactInfoController {

	@Autowired
	EmergencyContactInfoService emergencyContactInfoService;

	//save data
	@PostMapping("/saveEmergencyContact")
	public ResponseEntity<Object> saveEmergencyContact(@Valid @RequestBody EmergencyContactInfoDto emergencyContactInfoDto) throws Exception{

		return ResponseHandler.responseBuilder("Emergency contact is saved for Employee"
		+" " + emergencyContactInfoDto.getEmployeeName(),HttpStatus.OK,
				emergencyContactInfoService.saveEmergencyContact(emergencyContactInfoDto));


	}
@GetMapping("/getAllEmergencyContactInfo")
public List<EmergencyContactInfoDto> EmergencyContactInfo()
{
	return emergencyContactInfoService.EmergencyContactInfo();
}
}
