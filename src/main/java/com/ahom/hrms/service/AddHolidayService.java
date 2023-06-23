package com.ahom.hrms.service;

import java.text.ParseException;
import java.util.List;

import com.ahom.hrms.dto.AddHolidayDto;
import com.ahom.hrms.entities.AddHoliday;

public interface AddHolidayService {

	AddHolidayDto SaveAddHolidayDetail(AddHolidayDto addHolidayDto) throws ParseException;

	List<AddHolidayDto> getAllLeaveDetail();

	AddHoliday deleteLaeveDetail(int id);

	AddHoliday updateLeaveDetail(AddHolidayDto addHolidayDto,int id);

}
