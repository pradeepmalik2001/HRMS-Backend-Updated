package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BankingInfoRepository;
import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.entities.BankingInfo;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
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

//	@Autowired
//	WorkInformation workInformation;
@Autowired
WorkInformationRepository workInformationRepository;

	//save data
	public void saveBankingInfo(BankingInfoDto bankingInfoDto) throws Exception {
//		BasicEmployee employee=basicEmployeeRepository.findById(bankingInfoDto.getEmployeeId()).orElse(null);
		BankingInfo bankingInfo = bankingInfoRepository.findById(bankingInfoDto.getEmployeeId()).orElse(null);
		if (bankingInfo == null) {

			bankingInfoDto.setBasicSalary(bankingInfoDto.getBasicSalary());
			bankingInfoRepository.saveAndFlush(bankingInfoDtoToBankingInfo(bankingInfoDto));
			throw new CustomException("Data Saved");
		} else {
			throw new CustomException("Data is Already Present");
		}
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

//		BasicEmployee basicEmployee= (BasicEmployee) basicEmployeeRepository.findAll();
//		double grossSalary = (double) (basicEmployee.getCtc()) /12;
//		double basicSalary=grossSalary*0.6;
//		System.out.println(basicSalary);
//		System.out.println(grossSalary);
		return bankingInfoRepository.findAll();
	}

	//	@Override
//	public List<BankingInfo> getBankInfo() {
//
//		List list=this.bankingInfoRepository.findAll();
//		return list;
//		List<BankingInfo>get=this.bankingInfoRepository.findAll();
//		 List<BankingInfoDto> getBank=get.stream().map(li-> {
//			 try {
//				 return this.bankingInfoToBankingInfoDto(li);
//			 } catch (Exception e) {
//				 throw new RuntimeException(e);
//			 }
//		 }).collect(Collectors.toList());
//		 return getBank;
//	}
	@Override
	public BankingInfoDto getById(Integer employeeId) throws Exception {
		BankingInfo byId = bankingInfoRepository.findAllById(employeeId);
//		BasicEmployee basicEmployee=basicEmployeeRepository.findById(employeeId).orElse(null);
//		if (basicEmployee!=null)
//		{
//			byId.setBasicEmployee1(basicEmployee);
		return bankingInfoToBankingInfoDto(byId);
//		}else
//			return null;

	}


}
