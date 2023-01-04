package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.RoleDto;
import com.ahom.hrms.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

	//save data in database
	@PostMapping("/saveRole")
	public ResponseEntity<RoleDto> SaveRole(@RequestBody RoleDto roleDto){
		roleService.saveRole(roleDto);
		return new ResponseEntity<>(roleDto, HttpStatus.CREATED);
	}
}
