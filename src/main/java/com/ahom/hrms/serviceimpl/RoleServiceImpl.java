package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.RoleRepository;
import com.ahom.hrms.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.RoleDto;

import com.ahom.hrms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	//save data
	public void saveRole(RoleDto roleDto) {
		roleRepository.save(roleDtoToRole(roleDto));
	}
	
	//converting DTO
	public Role roleDtoToRole(RoleDto roleDto) {
		Role role = this.modelMapper.map(roleDto, Role.class);
		return role;
	}
	
	public RoleDto roleToRoleDto(Role role) {
		RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);
		return roleDto;
	}

}
