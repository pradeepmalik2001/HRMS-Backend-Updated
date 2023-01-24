package com.ahom.hrms.service;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.entities.BankingInfo;

import java.util.List;

public interface BankingInfoService {
	
	void saveBankingInfo(BankingInfoDto bankingInfoDto);
	
	BankingInfo bankingInfoDtoToBankingInfo(BankingInfoDto bankingInfoDto);
	
	BankingInfoDto bankingInfoToBankingInfoDto(BankingInfo bankingInfo);
	List<BankingInfoDto>getBankInfo();
	BankingInfo getByName(String name);

}
