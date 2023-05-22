package com.ahom.hrms.service;

import com.ahom.hrms.dto.AddDepartmentDto;
import com.ahom.hrms.entities.AddDepartment;

import java.util.List;
import java.util.Optional;

public interface AddDepartmentService {
	
	AddDepartmentDto saveDepartment(AddDepartmentDto addDepartmentDto);
	
	AddDepartment addDepartmentDtoToAddDepartment(AddDepartmentDto addDepartmentDto);
	
	AddDepartmentDto addDepartmentToAddDepartmentDto(AddDepartment addDepartment);

	List<AddDepartmentDto> getALlUser();

	AddDepartment delete(int id);

	public Optional<AddDepartment> getDepartmentById(int departmentId);

}
