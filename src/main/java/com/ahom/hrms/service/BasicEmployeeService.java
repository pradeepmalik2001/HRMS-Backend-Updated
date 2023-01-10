package com.ahom.hrms.service;

import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.entities.BasicEmployee;

import java.util.List;

public interface BasicEmployeeService {
	
	void saveEmployee(BasicEmployeeDto basicEmployeeDto);
	
	BasicEmployeeDto employeeById(int employeeId);
	
	BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto);
	
	BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee);

	List<BasicEmployeeDto> getAllEmployee();

}
