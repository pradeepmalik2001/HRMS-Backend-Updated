package com.ahom.hrms.service;


import java.util.Date;
import java.util.List;

import com.ahom.hrms.dto.OverTimeDto;
import com.ahom.hrms.entities.OverTime;

public interface OverTimeService {
	
	public void EmployeeSave(OverTime overtimedto);
	public List<OverTimeDto> Employeefetch();
	public List<OverTime> gteOt(String startdate
			, String enddate
			, String name);

}
