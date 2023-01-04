package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.EmergencyContactInfoRepository;
import com.ahom.hrms.entities.EmergencyContactInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.EmergencyContactInfoDto;

import com.ahom.hrms.service.EmergencyContactInfoService;

@Service
public class EmergencyContactInfoServiceImpl implements EmergencyContactInfoService{

	@Autowired
	EmergencyContactInfoRepository emergencyContactInfoRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public void saveEmergencyContact(EmergencyContactInfoDto emergencyContactInfoDto) {
		emergencyContactInfoRepository.save(emergencyContactInfoDtoToEmergencyContactInfo(emergencyContactInfoDto));
	}

	//converting DTO
	public EmergencyContactInfo emergencyContactInfoDtoToEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) {
		EmergencyContactInfo emergencyContactInfo = this.modelMapper.map(emergencyContactInfoDto, EmergencyContactInfo.class);
		return emergencyContactInfo;
	}

	public EmergencyContactInfoDto emergencyContactInfoToEmergencyContactInfoDto(EmergencyContactInfo emergencyContactInfo) {
		EmergencyContactInfoDto emergencyContactInfoDto = this.modelMapper.map(emergencyContactInfo, EmergencyContactInfoDto.class);
		return emergencyContactInfoDto;
	}

}
