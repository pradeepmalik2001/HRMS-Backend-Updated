package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AddHolidayDto;

public interface AddHolidayService {

	void SaveAddHolidayDetail(AddHolidayDto addHolidayDto);

	List<AddHolidayDto> getAllLeaveDetail();

	void deleteLaeveDetail(int i);

	void updateLeaveDetail(AddHolidayDto addHolidayDto,int id);

}
