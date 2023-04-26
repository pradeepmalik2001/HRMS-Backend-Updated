package com.ahom.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.service.EmergencyContactInfoService;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class EmergencyContactInfoController {

	@Autowired
	EmergencyContactInfoService emergencyContactInfoService;

	//save data
	@PostMapping("/saveemergencycontact")
	public ResponseEntity<EmergencyContactInfoDto> saveEmergencyContact(@Valid @RequestBody EmergencyContactInfoDto emergencyContactInfoDto) throws Exception{
		emergencyContactInfoService.saveEmergencyContact(emergencyContactInfoDto);
		return new ResponseEntity<>(emergencyContactInfoDto, HttpStatus.CREATED);
	}
@GetMapping("/getallemergencyContactInfo")
public List<EmergencyContactInfoDto> EmergencyContactInfo()
{
	return emergencyContactInfoService.EmergencyContactInfo();
}
}
