package com.ahom.hrms.service;

import com.ahom.hrms.dto.EmployementTypeMasterDto;
import com.ahom.hrms.entities.EmployementTypeMaster;

import java.util.List;

public interface EmployementTypeMasterService {
	
	Object saveEmployement(EmployementTypeMasterDto employementTypeMasterDto);
	
	EmployementTypeMaster employementTypeMasterDtoToEmployementTypeMaster(EmployementTypeMasterDto employementTypeMasterDto);
	
	EmployementTypeMasterDto employementTypeMasterToUserEmployementTypeMasterDto(EmployementTypeMaster employementTypeMaster);

	///
	List<EmployementTypeMasterDto> getAll();

	EmployementTypeMaster deleteEMployement(int id);

	EmployementTypeMaster updateEmployement(EmployementTypeMaster employementTypeMaster,int id);
}
