package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.entities.EmergencyContactInfo;

public interface EmergencyContactInfoService {
	
	Object saveEmergencyContact(EmergencyContactInfoDto emergencyContactInfoDto) throws Exception;
	
	EmergencyContactInfo emergencyContactInfoDtoToEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) throws Exception;
	
	EmergencyContactInfoDto emergencyContactInfoToEmergencyContactInfoDto(EmergencyContactInfo emergencyContactInfo);
	
	List<EmergencyContactInfoDto> EmergencyContactInfo();

	EmergencyContactInfoDto emergencyContactInfoDto(String id, EmergencyContactInfoDto  emergencyContactInfoDto);

}
