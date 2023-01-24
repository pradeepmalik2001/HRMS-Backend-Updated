package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BankingInfoRepository;
import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.entities.BankingInfo;
import com.ahom.hrms.entities.BasicEmployee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BankingInfoDto;

import com.ahom.hrms.service.BankingInfoService;

import java.util.List;

@Service
public class BankingInfoServiceImpl implements BankingInfoService{

	@Autowired
	BankingInfoRepository bankingInfoRepository;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BasicEmployeeRepository basicEmployeeRepository;

	//save data
	public void saveBankingInfo(BankingInfoDto bankingInfoDto) {
		bankingInfoRepository.save(bankingInfoDtoToBankingInfo(bankingInfoDto));
	}

	//converting DTO
	public BankingInfo bankingInfoDtoToBankingInfo(BankingInfoDto bankingInfoDto) {
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(bankingInfoDto.getBasicId()).orElse(null);
		BankingInfo bankingInfo = this.modelMapper.map(bankingInfoDto, BankingInfo.class);
		bankingInfo.setBasicEmployee(basicEmployee);
		return bankingInfo;
	}

	public BankingInfoDto bankingInfoToBankingInfoDto(BankingInfo bankingInfo) {
		BankingInfoDto bankingInfoDto = this.modelMapper.map(bankingInfo, BankingInfoDto.class);
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(bankingInfoDto.getBasicId())
				.orElse(null);
		bankingInfo.setBasicEmployee(basicEmployee);
		return bankingInfoDto;
	}

	@Override
	public List<BankingInfoDto> getBankInfo() {
		List all = bankingInfoRepository.findAll();
		return all;
	}

	@Override
	public BankingInfo getByName(String name) {
		BankingInfo byName = bankingInfoRepository.findByName(name);
		return byName;
	}

}
