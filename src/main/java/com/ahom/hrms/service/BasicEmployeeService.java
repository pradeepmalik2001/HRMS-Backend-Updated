package com.ahom.hrms.service;

import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.entities.BasicEmployee;

import java.text.ParseException;
import java.util.List;

public interface BasicEmployeeService {
	
	void saveEmployee(BasicEmployeeDto basicEmployeeDto) throws ParseException;
	
	BasicEmployeeDto employeeById(int employeeId);
	
	BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto) throws ParseException;
	
	BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee);

	List<BasicEmployee> getAllEmployee();
	List<BasicEmployee>details(int id);

	void deleteEmployee(int id);

}
