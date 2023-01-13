package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.RoleRepository;
import com.ahom.hrms.Repository.UserMasterRepository;
import com.ahom.hrms.dto.RoleDto;
import com.ahom.hrms.entities.Role;
import com.ahom.hrms.entities.UserMaster;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.UserMasterDto;

import com.ahom.hrms.service.UserMasterService;

import java.util.Collections;
import java.util.List;

@Service
public class UserMasterServiceImpl implements UserMasterService{
	
	@Autowired
	UserMasterRepository userMasterRepository;
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RoleRepository roleRepository;

	
	//save data
	public UserMaster saveUser(UserMaster userMasterDto) {


		Role role=roleRepository.findByRoleName(userMasterDto.getRoleName());
		if (role==null)
			throw new RuntimeException("No role");
		else {
//			userMasterDto.setRoleName(role.getRoleName());
			userMasterDto.setRoles(Collections.singletonList(role));
			userMasterDto.setRoleName(userMasterDto.getRoleName());

			userMasterRepository.save(userMasterDto);
		}
		return userMasterDto;
	}
	
	//fetch data by UserName
	public UserMasterDto fetchByUser(String userName) {
		UserMaster userMaster = this.userMasterRepository.findByUserName(userName);
		return userMasterToUserMasterDto(userMaster);
	}
	
	//update data
	public UserMasterDto updateUser(UserMasterDto userMasterDto) {
		userMasterRepository.save(userMasterDtoToUserMaster(userMasterDto));
		return userMasterDto;
	}
	
	//converting DTO
	public UserMaster userMasterDtoToUserMaster(UserMasterDto userMasterDto) {
		UserMaster userMaster = this.modelMapper.map(userMasterDto, UserMaster.class);
		return userMaster;
	}
	
	public UserMasterDto userMasterToUserMasterDto(UserMaster userMaster) {
		UserMasterDto userMasterDto = this.modelMapper.map(userMaster, UserMasterDto.class);
		return userMasterDto;
	}

//	@Override
//	public List<UserMasterDto> getALlUser() {
//		List list=this.userMasterRepository.findAll();
//		return list;
//	}


}
