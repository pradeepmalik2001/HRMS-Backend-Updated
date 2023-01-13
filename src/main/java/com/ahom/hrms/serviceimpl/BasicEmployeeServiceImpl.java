package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.dto.EmployeeTrainingDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.EmployeeTraining;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BasicEmployeeDto;

import com.ahom.hrms.service.BasicEmployeeService;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class BasicEmployeeServiceImpl implements BasicEmployeeService{
	
	@Autowired
	BasicEmployeeRepository basicEmployeeRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	//save data
	public void saveEmployee(BasicEmployeeDto basicEmployeeDto) {
		basicEmployeeRepository.save(basicEmployeeDtoToBasicEmployee(basicEmployeeDto));
	}
	
	//fetch data by employee id
	public BasicEmployeeDto employeeById(int employeeId){
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(employeeId).get();
		return basicEmployeeToBasicEmployeeDto(basicEmployee);
	}


	public List<BasicEmployeeDto> getAllEmployee()
	{
		List list=this.basicEmployeeRepository.findAll();
		 return list;
	}

	//converting DTO
	public BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto) {
		BasicEmployee basicEmployee = this.modelMapper.map(basicEmployeeDto, BasicEmployee.class);
		return basicEmployee;
	}
	
	public BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee) {
		BasicEmployeeDto basicEmployeeDto = this.modelMapper.map(basicEmployee, BasicEmployeeDto.class);
		return basicEmployeeDto;
	}


	
	public List<BasicEmployeeDto> getAll() {
		List list=basicEmployeeRepository.findAll();
		return list;
	}



}
