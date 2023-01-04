package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.MonthlyPerformanceRepository;
import com.ahom.hrms.entities.MonthlyPerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.MonthlyPerformanceDto;

import com.ahom.hrms.service.MonthlyPerformanceService;

@Service
public class MonthlyPerformanceServiceImpl implements MonthlyPerformanceService {
	
	@Autowired
	MonthlyPerformanceRepository monthlyPerformanceRepository;
	
	
	@Override
	 public void saveMonthlyPerformance(MonthlyPerformanceDto monthlyPerformanceDto)
	    {
	        monthlyPerformanceRepository.save(monthlyPerformancedtotoMonthlyPerformance(monthlyPerformanceDto));

	    }
	
	
	 
	 @Override
	 public List<MonthlyPerformanceDto> getAllMonthlyPerformance(){

	        List<MonthlyPerformance> listMonthlyPerformances= this.monthlyPerformanceRepository.findAll();

	        List<MonthlyPerformanceDto> userDtoList = listMonthlyPerformances.stream().map(emp -> this.monthlyPerformancetoMonthlyPerformancedto(emp)).collect(Collectors.toList());

	        

	        return userDtoList;
	    }
	 
	 @Override
	 public void deleteMonthlyPerformance(int empId){

	        monthlyPerformanceRepository.deleteById(empId);

	    }
	 
	 @Override
	 public MonthlyPerformanceDto updateMonthlyPerformance(MonthlyPerformanceDto monthlyPerformanceDto)
	    {
	    monthlyPerformanceRepository.save(monthlyPerformancedtotoMonthlyPerformance(monthlyPerformanceDto));
	    return monthlyPerformanceDto;

	    }

	
	
	
	 public MonthlyPerformance monthlyPerformancedtotoMonthlyPerformance(MonthlyPerformanceDto monthlyPerformanceDto) {
		 
		 MonthlyPerformance monthlyPerformance=new MonthlyPerformance();

		 monthlyPerformance.setDateOfJoining(monthlyPerformanceDto.getDateOfJoining());
		 monthlyPerformance.setDepartment(monthlyPerformanceDto.getDepartment());
		 monthlyPerformance.setDescription(monthlyPerformanceDto.getDescription());
		 monthlyPerformance.setDesignation(monthlyPerformanceDto.getDesignation());
		 monthlyPerformance.setEmployeeId(monthlyPerformanceDto.getEmployeeId());
		 monthlyPerformance.setName(monthlyPerformanceDto.getName());
		 monthlyPerformance.setReportAuth(monthlyPerformanceDto.getReportAuth());
		 monthlyPerformance.setReportingMonth(monthlyPerformanceDto.getReportingMonth());
		 monthlyPerformance.setReportingTime(monthlyPerformanceDto.getReportingTime());
		 monthlyPerformance.setStatus(monthlyPerformanceDto.getStatus());
		 
		return monthlyPerformance;
		 
	 }
	 
	 public MonthlyPerformanceDto monthlyPerformancetoMonthlyPerformancedto(MonthlyPerformance monthlyPerformance) {
		 
		 MonthlyPerformanceDto monthlyPerformanceDto =new MonthlyPerformanceDto();
		 
		 monthlyPerformanceDto.setDateOfJoining(monthlyPerformance.getDateOfJoining());
		 monthlyPerformanceDto.setDepartment(monthlyPerformance.getDepartment());
		 monthlyPerformanceDto.setDescription(monthlyPerformance.getDescription());
		 monthlyPerformanceDto.setDesignation(monthlyPerformance.getDesignation());
		 monthlyPerformanceDto.setEmployeeId(monthlyPerformance.getEmployeeId());
		 monthlyPerformanceDto.setName(monthlyPerformance.getName());
		 monthlyPerformanceDto.setReportAuth(monthlyPerformance.getReportAuth());
		 monthlyPerformanceDto.setReportingMonth(monthlyPerformance.getReportingMonth());
		 monthlyPerformanceDto.setReportingMonth(monthlyPerformance.getReportingMonth());
		 monthlyPerformanceDto.setReportingTime(monthlyPerformance.getReportingTime());
		 monthlyPerformanceDto.setStatus(monthlyPerformance.getStatus());
		 
		 
		return monthlyPerformanceDto;
		 
	 }

}
