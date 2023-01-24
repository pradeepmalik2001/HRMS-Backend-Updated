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
@CrossOrigin
@RequestMapping("/bank")
public class BankingInfoController {

	@Autowired
	BankingInfoService bankingInfoService;

	//save data
	@PostMapping("/savebankinginfo")
	public ResponseEntity<BankingInfoDto> saveBankingInfo(@RequestBody BankingInfoDto bankingInfoDto){
		bankingInfoService.saveBankingInfo(bankingInfoDto);
		return new ResponseEntity<>(bankingInfoDto, HttpStatus.CREATED);
	}
	@GetMapping("/getBankInfo")
	public ResponseEntity<List<BankingInfoDto>>response(){
		return
				new ResponseEntity<>(this.bankingInfoService.getBankInfo(),HttpStatus.ACCEPTED);
	}
	@GetMapping("/getByName/{name}")
	public BankingInfo response(@PathVariable("name")String name){
		return bankingInfoService.getByName(name);
	}

}
