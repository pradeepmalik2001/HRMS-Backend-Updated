package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.LoanAppDto;
import com.ahom.hrms.entities.LoanApp;

public interface LoanAppService {
	
    //postmapping
	void saveLoanApp(LoanAppDto loanAppDto);
	
 //getmapping
	List<LoanApp> getAllLoanApp();

}
