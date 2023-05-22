package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AddHolidayDto;
import com.ahom.hrms.entities.AddHoliday;

public interface AddHolidayService {

	Object SaveAddHolidayDetail(AddHolidayDto addHolidayDto);

	List<AddHolidayDto> getAllLeaveDetail();

	AddHoliday  deleteLaeveDetail(int id);

	Object updateLeaveDetail(AddHolidayDto addHolidayDto, int id);

}
