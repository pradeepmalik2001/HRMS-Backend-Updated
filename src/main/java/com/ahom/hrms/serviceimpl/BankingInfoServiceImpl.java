package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BankingInfoRepository;
import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.entities.BankingInfo;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.exception.CustomException;
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

@Autowired
WorkInformationRepository workInformationRepository;

	//save data
	public Object saveBankingInfo(BankingInfoDto bankingInfoDto) throws Exception {
		BankingInfo bankingInfo = bankingInfoRepository.findById(bankingInfoDto.getEmployeeId()).orElse(null);
		if (bankingInfo == null) {
			bankingInfoDto.setBasicSalary(bankingInfoDto.getBasicSalary());
			bankingInfoRepository.saveAndFlush(bankingInfoDtoToBankingInfo(bankingInfoDto));
		} else {
			throw new RuntimeException("Data is Already Present");
		}
		return bankingInfoDto;
	}


	//converting DTO
	public BankingInfo bankingInfoDtoToBankingInfo(BankingInfoDto bankingInfoDto) throws Exception {
		int empId=bankingInfoDto.getEmployeeId();
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(bankingInfoDto.getEmployeeId())
				.orElse(null);
		BankingInfo bankingInfo = this.modelMapper.map(bankingInfoDto, BankingInfo.class);
		if(basicEmployee!=null) {
			bankingInfo.setId(basicEmployee.getEmployeeId());
			bankingInfo.setBasicEmployee1(basicEmployee);
			bankingInfo.setGrossSalary((double) basicEmployee.getCtc() /12);

			bankingInfo.setBasicSalary(((double) basicEmployee.getCtc() /12)*0.6);

		} else {
			throw new Exception("employee name not found!!");
		}

		return bankingInfo;
	}

	public BankingInfoDto bankingInfoToBankingInfoDto(BankingInfo bankingInfo) throws Exception {
		BankingInfoDto bankingInfoDto = this.modelMapper.map(bankingInfo, BankingInfoDto.class);

		int empId=bankingInfoDto.getEmployeeId();
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(empId).orElse(null);
		return bankingInfoDto;
	}

	@Override
	public List<BankingInfo> getAllInfo() {

		return bankingInfoRepository.findAll();
	}

	@Override
	public BankingInfoDto getById(Integer employeeId) throws Exception {
		BankingInfo byId = bankingInfoRepository.findAllById(employeeId);
		if (byId!=null) {
			return bankingInfoToBankingInfoDto(byId);
		}else {
			throw new RuntimeException("Employee With ID:" +employeeId+" not found");
		}
	}


}
