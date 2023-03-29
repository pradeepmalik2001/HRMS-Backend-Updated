package com.ahom.hrms.controller;

import com.ahom.hrms.entities.BankingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.service.BankingInfoService;

import java.util.List;

@RestController

@RequestMapping("/bank")
public class BankingInfoController {

	@Autowired
	BankingInfoService bankingInfoService;

	//save data
	@PostMapping("/savebankinginfo")
	public ResponseEntity<BankingInfoDto> saveBankingInfo(@RequestBody BankingInfoDto bankingInfoDto) throws Exception{
		bankingInfoService.saveBankingInfo(bankingInfoDto);
		return new ResponseEntity<>(bankingInfoDto, HttpStatus.CREATED);
	}
	@GetMapping("/getBankInfo")
	public ResponseEntity<List<BankingInfo>>response() {

		return new ResponseEntity<>(this.bankingInfoService.getAllInfo(),HttpStatus.ACCEPTED);
	}
	@GetMapping("/getById/{employeeId}")
	public BankingInfoDto response(@PathVariable Integer employeeId) throws Exception {
		return bankingInfoService.getById(employeeId);
	}

}
