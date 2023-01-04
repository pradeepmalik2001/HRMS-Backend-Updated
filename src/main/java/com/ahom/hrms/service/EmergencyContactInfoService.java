package com.ahom.hrms.service;

import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.entities.EmergencyContactInfo;

public interface EmergencyContactInfoService {
	
	void saveEmergencyContact(EmergencyContactInfoDto emergencyContactInfoDto);
	
	EmergencyContactInfo emergencyContactInfoDtoToEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto);
	
	EmergencyContactInfoDto emergencyContactInfoToEmergencyContactInfoDto(EmergencyContactInfo emergencyContactInfo);

}
