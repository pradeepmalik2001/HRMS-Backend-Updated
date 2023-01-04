package com.ahom.hrms.service;

import com.ahom.hrms.dto.RoleDto;
import com.ahom.hrms.entities.Role;

public interface RoleService {
	
	void saveRole(RoleDto roleDto);
	
	Role roleDtoToRole(RoleDto roleDto);
	
	RoleDto roleToRoleDto(Role role);

}
