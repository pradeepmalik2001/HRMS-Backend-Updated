package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.service.BankingInfoService;

@RestController
@CrossOrigin
public class BankingInfoController {

	@Autowired
	BankingInfoService bankingInfoService;

	//save data
	@PostMapping("/savebankinginfo")
	public ResponseEntity<BankingInfoDto> saveBankingInfo(@RequestBody BankingInfoDto bankingInfoDto){
		bankingInfoService.saveBankingInfo(bankingInfoDto);
		return new ResponseEntity<>(bankingInfoDto, HttpStatus.CREATED);
	}

}
