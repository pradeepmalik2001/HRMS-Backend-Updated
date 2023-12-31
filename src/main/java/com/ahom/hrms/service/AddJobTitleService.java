package com.ahom.hrms.service;

import java.util.List;

import com.ahom.hrms.dto.AddJobTitleDto;
import com.ahom.hrms.entities.AddJobTitle;

public interface AddJobTitleService {
	
AddJobTitleDto saveTitle(AddJobTitleDto addJobTitleDto);

List<AddJobTitleDto> getJob();

AddJobTitle getById(int id);

AddJobTitle addJobTitleDtoToaddJobTitle(AddJobTitleDto addJobTitleDto);

AddJobTitleDto jobToJobDto(AddJobTitle addJobTitle);


}
