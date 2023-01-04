package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.MonthlyPerformanceDto;

public interface MonthlyPerformanceService {

	void saveMonthlyPerformance(MonthlyPerformanceDto monthlyPerformanceDto);

	List<MonthlyPerformanceDto> getAllMonthlyPerformance();

	void deleteMonthlyPerformance(int empId);

	MonthlyPerformanceDto updateMonthlyPerformance(MonthlyPerformanceDto monthlyPerformanceDto);

}
