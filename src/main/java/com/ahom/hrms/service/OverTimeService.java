package com.ahom.hrms.service;


import java.util.List;

import com.ahom.hrms.dto.OverTimeDto;
import com.ahom.hrms.entities.OverTime;

public interface OverTimeService {
	
	OverTimeDto EmployeeSave(OverTimeDto overtimedto);
	public List<OverTimeDto> Employeefetch();
	public List<OverTime> gteOt(String month
			, String name);
	 int getByUserNameAndMonth(String month,String userName);

	 OverTime findByUserName(String userName);

}
