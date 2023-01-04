package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.entities.LoanApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.LoanAppDto;
import com.ahom.hrms.serviceimpl.LoanAppServiceImpl;


@RestController
@RequestMapping("/loan_application")
@CrossOrigin
public class LoanAppController {
	

	@Autowired
	LoanAppServiceImpl loanAppService;
	
	@PostMapping("/save")
	public LoanAppDto saveLoanApp(@RequestBody LoanAppDto loanAppDto) 
	{
		loanAppService.saveLoanApp(loanAppDto);
		return loanAppDto;
	}
	
	
	@GetMapping("/fatch")
	public List<LoanApp> getLoanApp()
	{
		List<LoanApp>allLoanApp=loanAppService.getAllLoanApp();
		return allLoanApp;
	}
	

	
	

}

