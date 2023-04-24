package com.ahom.hrms.service;

import com.ahom.hrms.dto.RoleDto;
import com.ahom.hrms.entities.Role;

import java.util.List;

public interface RoleService {
	
	void saveRole(RoleDto roleDto);
	
	Role roleDtoToRole(RoleDto roleDto);
	
	RoleDto roleToRoleDto(Role role);

	List<Role> fetchAll();

}
