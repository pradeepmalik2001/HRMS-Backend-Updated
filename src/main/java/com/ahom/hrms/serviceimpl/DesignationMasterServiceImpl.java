package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.DesignationMasterRepository;
import com.ahom.hrms.entities.DesignationMaster;
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
	public void saveDesignation(DesignationMasterDto designationMasterDto) {
		designationMasterRepository.save(designationMasterDtoToDesignationMaster(designationMasterDto));
	}

	//fetch all data
	public List<DesignationMasterDto> getAllEmployee(){
		List<DesignationMaster> listDesignation = this.designationMasterRepository.findAll();
		List<DesignationMasterDto> userToList = listDesignation.stream().map(designation -> this.designationMasterToDesignationMasterDto(designation)).collect(Collectors.toList());
		return userToList;
	}

	//converting DTO
	public DesignationMaster designationMasterDtoToDesignationMaster(DesignationMasterDto designationMasterDto) {
		DesignationMaster designationMaster = this.modelMapper.map(designationMasterDto, DesignationMaster.class);
		return designationMaster;
	}

	public DesignationMasterDto designationMasterToDesignationMasterDto(DesignationMaster designationMaster) {
		DesignationMasterDto designationMasterDto = this.modelMapper.map(designationMaster, DesignationMasterDto.class);
		return designationMasterDto;
	}

}
