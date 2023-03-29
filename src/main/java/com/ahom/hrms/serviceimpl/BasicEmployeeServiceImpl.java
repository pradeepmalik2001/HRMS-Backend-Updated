package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.dto.EmployeeTrainingDto;
import com.ahom.hrms.dto.SalarySetupDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.EmployeeTraining;
import com.ahom.hrms.entities.SalarySetup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BasicEmployeeDto;

import com.ahom.hrms.service.BasicEmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class BasicEmployeeServiceImpl implements BasicEmployeeService{

	@Autowired
	BasicEmployeeRepository basicEmployeeRepository;
	@Autowired
	SalarySetupRepository salarySetupRepository;
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


	public List<BasicEmployee> getAllEmployee()
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
//	public double hraCalculation(int id)
//	{
//		// int empId= salarySetupDtoTosalarySetup().getId();
//		BasicEmployee basId=basicEmployeeRepository.findById(id).orElseThrow(null);
//		SalarySetup byEmpId = salarySetupRepository.findById(basId.getEmployeeId());
//		double annualSalary = byEmpId.getAnnualSalary();
//		double hra=(annualSalary / 100) * 15;
//		return hra;+
//	}


//	public List<BasicEmployeeDto> getAll() {
//		List list=basicEmployeeRepository.findAll();
//		return list;
//	}

	public List<BasicEmployee>details(int id){
		List<BasicEmployee>list=basicEmployeeRepository.findByDetails(id);
		System.out.println(list);
		List<BasicEmployee>filterData=new ArrayList<>();

		for (BasicEmployee basicEmployee:list)
		{
			filterData.add(basicEmployee);
			System.out.println(basicEmployee);

		}
		return filterData;
	}



}
