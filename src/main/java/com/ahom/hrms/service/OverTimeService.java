package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.OverTimeDto;

public interface OverTimeService {
	
	public void EmployeeSave(OverTimeDto overtimedto);
	public List<OverTimeDto> Employeefetch();

}
