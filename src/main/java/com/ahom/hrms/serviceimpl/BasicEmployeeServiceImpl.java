package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.entities.BasicEmployee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BasicEmployeeDto;

import com.ahom.hrms.service.BasicEmployeeService;

import java.util.ArrayList;
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

		return this.basicEmployeeRepository.findAll();
	}


	//converting DTO
	public BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto) {
		return this.modelMapper.map(basicEmployeeDto, BasicEmployee.class);
	}

	public BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee) {
		return this.modelMapper.map(basicEmployee, BasicEmployeeDto.class);
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
		List<BasicEmployee>list=basicEmployeeRepository.findAllByDetails(id);
		System.out.println(list);
		List<BasicEmployee>filterData=new ArrayList<>();

		for (BasicEmployee basicEmployee:list)
		{
			filterData.add(basicEmployee);
			System.out.println(basicEmployee);

		}
		return filterData;
	}

	@Override
	public void deleteEmployee(int id) {
		basicEmployeeRepository.deleteById(id);
	}


}
