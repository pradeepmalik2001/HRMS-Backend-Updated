package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.DesignationMasterRepository;
import com.ahom.hrms.entities.DesignationMaster;
import com.ahom.hrms.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.DesignationMasterDto;

import com.ahom.hrms.service.DesignationMasterService;

@Service
public class DesignationMasterServiceImpl implements DesignationMasterService{

	@Autowired
	DesignationMasterRepository designationMasterRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public Object saveDesignation(DesignationMasterDto designationMasterDto) {
		DesignationMaster designationMaster=designationMasterRepository.findByDesignationName(designationMasterDto.getDesignationName());
		if(designationMaster!=null)
		{
			throw new RuntimeException ("Designation Already Present");
		}
		else{
			return designationMasterRepository.
					save(designationMasterDtoToDesignationMaster(designationMasterDto));

		}

	}

	//fetch all data
	public List<DesignationMasterDto> getAllEmployee(){
		List<DesignationMaster> listDesignation = this.designationMasterRepository.findAll();
		return listDesignation.stream().map(this::designationMasterToDesignationMasterDto).collect(Collectors.toList());
	}

	//converting DTO
	public DesignationMaster designationMasterDtoToDesignationMaster(DesignationMasterDto designationMasterDto) {
		return this.modelMapper.map(designationMasterDto, DesignationMaster.class);
	}

	public DesignationMasterDto designationMasterToDesignationMasterDto(DesignationMaster designationMaster) {
		DesignationMasterDto designationMasterDto = this.modelMapper.map(designationMaster, DesignationMasterDto.class);
		return designationMasterDto;
	}

	@Override
	public DesignationMaster deleteDesignation(int id)
	{
		DesignationMaster designationMaster=designationMasterRepository.findById(id).orElse(null);
		if(designationMaster!=null)
		{
			designationMasterRepository.deleteById(id);

		}
		else {
			throw new RuntimeException("Designation for ID:"+id+ " " +"not found");
		}
		return designationMaster;
	}

}
