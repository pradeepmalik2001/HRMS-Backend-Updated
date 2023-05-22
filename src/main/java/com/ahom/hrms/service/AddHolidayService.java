package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AddHolidayDto;
import com.ahom.hrms.entities.AddHoliday;

public interface AddHolidayService {

	AddHolidayDto SaveAddHolidayDetail(AddHolidayDto addHolidayDto);

	List<AddHolidayDto> getAllLeaveDetail();

	AddHoliday deleteLaeveDetail(int i);

	AddHoliday updateLeaveDetail(AddHolidayDto addHolidayDto,int id);

}
