package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BankingInfoRepository;
import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.WorkInformationRepository;
import com.ahom.hrms.entities.BankingInfo;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahom.hrms.dto.BankingInfoDto;
import com.ahom.hrms.service.BankingInfoService;
import java.util.List;
import java.util.stream.Collectors;

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
	bankingInfoRepository.save(bankingInfoDtoToBankingInfo(bankingInfoDto));
	}

	//converting DTO
	public BankingInfo bankingInfoDtoToBankingInfo(BankingInfoDto bankingInfoDto) throws Exception {
		int empId=bankingInfoDto.getEmployeeId();
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(empId).orElse(null);
		BankingInfo bankingInfo = this.modelMapper.map(bankingInfoDto, BankingInfo.class);
		if(basicEmployee!=null)
		{
			bankingInfo.setBasicEmployee(basicEmployee);
//			String pfAcc=workInformation.getPfAccountNo();
//           bankingInfoDto.setPfAcc(pfAcc);
		}
		else
		{
			throw new Exception("employee id not found!!");
		}
	
		return bankingInfo;
	}

	public BankingInfoDto bankingInfoToBankingInfoDto(BankingInfo bankingInfo) throws Exception {
		BankingInfoDto bankingInfoDto = this.modelMapper.map(bankingInfo, BankingInfoDto.class);

		int empId=bankingInfoDto.getEmployeeId();
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(empId).orElse(null);
		WorkInformation workInformation=workInformationRepository.findById(empId).orElse(null);
		String pfAcc=workInformation.getPfAccountNo();
		if( workInformation!=null)
		{

			bankingInfoDto.setBasicEmployee(basicEmployee);

			bankingInfoDto.setPfAcc(pfAcc);
		}
		else
		{
			throw new Exception("employee id not found!!");
		}
		return bankingInfoDto;
	}

	@Override
	public List<BankingInfoDto> getBankInfo()  {

		List<BankingInfo>get=this.bankingInfoRepository.findAll();
		 List<BankingInfoDto> getBank=get.stream().map(li-> {
			 try {
				 return this.bankingInfoToBankingInfoDto(li);
			 } catch (Exception e) {
				 throw new RuntimeException(e);
			 }
		 }).collect(Collectors.toList());
		 return getBank;
	}
	@Override
	public BankingInfoDto getById(Integer employeeId) throws Exception {
		BankingInfo byId = bankingInfoRepository.findAllById(employeeId);
		return bankingInfoToBankingInfoDto(byId);
	}


}
