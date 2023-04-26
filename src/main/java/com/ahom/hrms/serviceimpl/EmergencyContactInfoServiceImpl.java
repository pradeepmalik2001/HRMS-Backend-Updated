package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.EmergencyContactInfoRepository;
import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.EmergencyContactInfo;
import com.ahom.hrms.service.EmergencyContactInfoService;

@Service
public class EmergencyContactInfoServiceImpl implements EmergencyContactInfoService{

	@Autowired
	EmergencyContactInfoRepository emergencyContactInfoRepository;


	@Autowired
	private BasicEmployeeRepository basicEmployeeRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public void saveEmergencyContact(EmergencyContactInfoDto emergencyContactInfoDto) throws Exception {
		BasicEmployee employee=basicEmployeeRepository.findByEmployeeName(emergencyContactInfoDto.getEmployeeName());
		EmergencyContactInfo contactInfo=emergencyContactInfoRepository.findByEmployeeName(emergencyContactInfoDto.getEmployeeName());
		if(employee!=null) {
			if(contactInfo!=null)
			{
				throw new CustomException("Data Already Exist");
			}
			emergencyContactInfoRepository.save(emergencyContactInfoDtoToEmergencyContactInfo(emergencyContactInfoDto));
		} else
		{
			throw new CustomException("Please Enter Correct Name");
		}

	}

	//converting DTO
	public EmergencyContactInfo emergencyContactInfoDtoToEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) throws Exception {
		EmergencyContactInfo emergencyContactInfo = this.modelMapper.map(emergencyContactInfoDto, EmergencyContactInfo.class);
		BasicEmployee basicEmployee=basicEmployeeRepository.findByEmployeeName(emergencyContactInfoDto.getEmployeeName());
		if(basicEmployee!=null) {
			emergencyContactInfo.setBasicEmployee(basicEmployee);
		} else {
			throw new Exception("employee name not found!!");
		}
		return emergencyContactInfo;
	}

	public EmergencyContactInfoDto emergencyContactInfoToEmergencyContactInfoDto(EmergencyContactInfo emergencyContactInfo) {
		EmergencyContactInfoDto emergencyContactInfoDto = this.modelMapper.map(emergencyContactInfo, EmergencyContactInfoDto.class);
		return emergencyContactInfoDto;
	}

	public List<EmergencyContactInfoDto> EmergencyContactInfo() {
		List<EmergencyContactInfo> salest=emergencyContactInfoRepository.findAll();
		List<EmergencyContactInfoDto> salesdt=salest.stream().map((EmergencyContactInfo)->modelMapper.map(EmergencyContactInfo, EmergencyContactInfoDto.class)).collect(Collectors.toList());
		return salesdt;
	}
}
