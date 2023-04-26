package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.BasicEmployeeDto;

import com.ahom.hrms.service.BasicEmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		BasicEmployee basicEmployee = basicEmployeeRepository.
				findByAadhaarNumberAndPanNumberAndPfnumberAndMobileAndEmail(
						basicEmployeeDto.getAadhaarNumber(),
						basicEmployeeDto.getPanNumber(),
						basicEmployeeDto.getPfnumber(),
						basicEmployeeDto.getMobile(),
						basicEmployeeDto.getEmail());

		if (basicEmployee==null){
			basicEmployeeRepository.save(basicEmployeeDtoToBasicEmployee(basicEmployeeDto));
		}else {
			throw
					new CustomException("something went wrong");
		}

//		if (Objects.equals(basicEmployeeDto.getAadhaarNumber(), basicEmployee.getAadhaarNumber()))
//		{
//			if (Objects.equals(basicEmployeeDto.getPanNumber(),basicEmployee.getPanNumber()))
//			{
//			throw new CustomException("Pan preent");
//			}
//
//		}	throw new CustomException("aadhaar present");

	}

	//fetch data by employee id
	public BasicEmployeeDto employeeById(int employeeId){
		BasicEmployee basicEmployee = basicEmployeeRepository.findById(employeeId).get();
		return basicEmployeeToBasicEmployeeDto(basicEmployee);
	}


	public List<BasicEmployee> getAllEmployee() {

		return this.basicEmployeeRepository.findAll();
	}


	//converting DTO
	public BasicEmployee basicEmployeeDtoToBasicEmployee(BasicEmployeeDto basicEmployeeDto) {
		return this.modelMapper.map(basicEmployeeDto, BasicEmployee.class);
	}

	public BasicEmployeeDto basicEmployeeToBasicEmployeeDto(BasicEmployee basicEmployee) {
		return this.modelMapper.map(basicEmployee, BasicEmployeeDto.class);
	}

	public List<BasicEmployee>details(int id){
		List<BasicEmployee>list=basicEmployeeRepository.findAllByDetails(id);
		System.out.println(list);
		List<BasicEmployee>filterData=new ArrayList<>();

		for (BasicEmployee basicEmployee:list) {
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
