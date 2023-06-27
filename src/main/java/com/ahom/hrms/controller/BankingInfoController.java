package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.entities.BankingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.service.BankingInfoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin

@RequestMapping("/bank")
public class BankingInfoController {

	@Autowired
	BankingInfoService bankingInfoService;

	//save data
	@PostMapping("/saveBankingInfo")
	public ResponseEntity<Object> saveBankingInfo(@Valid @RequestBody BankingInfoDto bankingInfoDto) throws Exception{

		return ResponseHandler.responseBuilder("Bank data saved successfully",HttpStatus.OK,
				bankingInfoService.saveBankingInfo(bankingInfoDto));

	}
	@GetMapping("/getBankInfo")
	public ResponseEntity<List<BankingInfo>>response() {

		return new ResponseEntity<>(this.bankingInfoService.getAllInfo(),HttpStatus.ACCEPTED);
	}
	@GetMapping("/getById/{employeeId}")
	public ResponseEntity<Object> response(@PathVariable Integer employeeId) throws Exception {
		return ResponseHandler.responseBuilder("Data for employee ID:" +employeeId
				+"-",HttpStatus.ACCEPTED,bankingInfoService.getById(employeeId));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object>updateWork(@PathVariable String id,
											@RequestBody BankingInfoDto bankingInfoDto){
		return ResponseHandler.responseBuilder("Update successfully",HttpStatus.OK,
				bankingInfoService.update(id, bankingInfoDto));
	}

}
