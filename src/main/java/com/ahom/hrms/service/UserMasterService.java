package com.ahom.hrms.service;

import com.ahom.hrms.dto.UserMasterDto;
import com.ahom.hrms.entities.UserMaster;

import java.util.List;

public interface UserMasterService {
	
	UserMaster saveUser(UserMaster userMasterDto);
	
	UserMasterDto fetchByUser(String userName);
	
	UserMasterDto updateUser(UserMasterDto userMasterDto);
	
	UserMaster userMasterDtoToUserMaster(UserMasterDto userMasterDto);
	
	UserMasterDto userMasterToUserMasterDto(UserMaster userMaster);

//	List<UserMasterDto> getALlUser();

}
