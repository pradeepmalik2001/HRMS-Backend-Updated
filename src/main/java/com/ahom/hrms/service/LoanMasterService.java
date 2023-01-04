package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.LoanMasterDto;
import com.ahom.hrms.entities.LoanMaster;

public interface LoanMasterService {
	
    //postmapping
	void saveLoanMaster(LoanMasterDto loanMasterDto);
		
   //getmapping
	List<LoanMaster> getAllLoanMaster();

}
