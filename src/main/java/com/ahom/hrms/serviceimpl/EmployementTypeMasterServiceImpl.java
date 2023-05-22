package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.EmployementTypeMasterRepository;
import com.ahom.hrms.dto.EmployementTypeMasterDto;
import com.ahom.hrms.entities.EmployementTypeMaster;
import com.ahom.hrms.service.EmployementTypeMasterService;

import java.util.List;

@Service
public class EmployementTypeMasterServiceImpl implements EmployementTypeMasterService{

	@Autowired
	EmployementTypeMasterRepository employementTypeMasterRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public Object saveEmployement(EmployementTypeMasterDto employementTypeMasterDto) {
		EmployementTypeMaster typeMaster=employementTypeMasterRepository.findByEmploymentType(employementTypeMasterDto.getEmploymentType());
		if(typeMaster==null)
		{
			employementTypeMasterRepository.save(employementTypeMasterDtoToEmployementTypeMaster(employementTypeMasterDto));
		}
		else {
			throw new RuntimeException("Employment Type is already present");
		}
		return employementTypeMasterDto;
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

//fetch
public List<EmployementTypeMasterDto> getAll() {
	List list=employementTypeMasterRepository.findAll();
	return list;
}

	@Override
	public EmployementTypeMaster deleteEMployement(int id)
	{
		EmployementTypeMaster employementTypeMaster=employementTypeMasterRepository.findById(id).orElse(null);
		if(employementTypeMaster!=null)
		{
			employementTypeMasterRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Employment Type for Id : "+id+" Not Found ");
		}
		return employementTypeMaster;
	}

}
