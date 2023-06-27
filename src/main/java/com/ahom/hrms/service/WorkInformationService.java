package com.ahom.hrms.service;

import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.dto.WorkInformationDto;
import com.ahom.hrms.entities.WorkInformation;

import java.util.List;

public interface WorkInformationService {
	
	WorkInformation saveWorkInfo(WorkInformationDto workInformationDto) throws Exception;
	
	WorkInformation workInformationDtoToWorkInformation(WorkInformationDto workInformationDto) throws Exception;
	
	WorkInformationDto workInformationToWorkInformationDto(WorkInformation workInformation);
	WorkInformationDto updateWork(WorkInformationDto workInformationDto,String id);

	List<WorkInformationDto> getAll();
}
