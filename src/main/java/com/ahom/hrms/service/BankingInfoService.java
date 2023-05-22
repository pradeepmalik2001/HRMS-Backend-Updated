package com.ahom.hrms.service;

import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.entities.BankingInfo;

import java.util.List;

public interface BankingInfoService {
	
	Object saveBankingInfo(BankingInfoDto bankingInfoDto) throws Exception;
	
	BankingInfo bankingInfoDtoToBankingInfo(BankingInfoDto bankingInfoDto) throws Exception;
	
	BankingInfoDto bankingInfoToBankingInfoDto(BankingInfo bankingInfo) throws Exception;

	List<BankingInfo>getAllInfo();

    BankingInfoDto getById(Integer employeeId) throws Exception;
}
