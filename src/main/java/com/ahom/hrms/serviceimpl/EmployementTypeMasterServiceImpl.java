package com.ahom.hrms.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.EmployementTypeMasterRepository;
import com.ahom.hrms.dto.EmployementTypeMasterDto;
import com.ahom.hrms.entities.EmployementTypeMaster;
import com.ahom.hrms.service.EmployementTypeMasterService;

@Service
public class EmployementTypeMasterServiceImpl implements EmployementTypeMasterService{

	@Autowired
	EmployementTypeMasterRepository employementTypeMasterRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public void saveEmployement(EmployementTypeMasterDto employementTypeMasterDto) {
		employementTypeMasterRepository.save(employementTypeMasterDtoToEmployementTypeMaster(employementTypeMasterDto));
	}

	//converting DTO
	public EmployementTypeMaster employementTypeMasterDtoToEmployementTypeMaster(EmployementTypeMasterDto employementTypeMasterDto) {
		EmployementTypeMaster employementTypeMaster = this.modelMapper.map(employementTypeMasterDto, EmployementTypeMaster.class);
		return employementTypeMaster;
	}

	public EmployementTypeMasterDto employementTypeMasterToUserEmployementTypeMasterDto(EmployementTypeMaster employementTypeMaster) {
		EmployementTypeMasterDto employementTypeMasterDto = this.modelMapper.map(employementTypeMaster, EmployementTypeMasterDto.class);
		return employementTypeMasterDto;
	}

}
