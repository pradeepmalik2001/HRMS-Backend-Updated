package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.EmergencyContactInfoRepository;
import com.ahom.hrms.dto.EmergencyContactInfoDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.EmergencyContactInfo;
import com.ahom.hrms.service.EmergencyContactInfoService;

@Service
public class EmergencyContactInfoServiceImpl implements EmergencyContactInfoService{

	@Autowired
	EmergencyContactInfoRepository emergencyContactInfoRepository;


	@Autowired
	private BasicEmployeeRepository basicEmployeeRepository;

	@Autowired
	ModelMapper modelMapper;

	//save data
	public Object saveEmergencyContact(EmergencyContactInfoDto emergencyContactInfoDto) throws Exception {

		EmergencyContactInfo contactInfo = emergencyContactInfoRepository.
				findById(emergencyContactInfoDto.getId()).orElse(null);

		if (contactInfo != null) {
			throw new RuntimeException("Data Already Exist");
		}
		emergencyContactInfoRepository.save(emergencyContactInfoDtoToEmergencyContactInfo(emergencyContactInfoDto));
		return emergencyContactInfoDto;
	}



	//converting DTO
	public EmergencyContactInfo emergencyContactInfoDtoToEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) throws Exception {
		EmergencyContactInfo emergencyContactInfo = this.modelMapper.map(emergencyContactInfoDto, EmergencyContactInfo.class);
		BasicEmployee basicEmployee=basicEmployeeRepository.findById(emergencyContactInfoDto.getId()).orElse(null);
		if(basicEmployee!=null) {
			emergencyContactInfo.setId(basicEmployee.getEmployeeId());
			emergencyContactInfo.setBasicEmployee(basicEmployee);
		} else {
			throw new Exception("employee name not found!!");
		}
		return emergencyContactInfo;
	}

	public EmergencyContactInfoDto emergencyContactInfoToEmergencyContactInfoDto(EmergencyContactInfo emergencyContactInfo) {
		EmergencyContactInfoDto emergencyContactInfoDto = this.modelMapper.map(emergencyContactInfo, EmergencyContactInfoDto.class);
		return emergencyContactInfoDto;
	}

	public List<EmergencyContactInfoDto> EmergencyContactInfo() {
		List<EmergencyContactInfo> salest=emergencyContactInfoRepository.findAll();
		List<EmergencyContactInfoDto> salesdt=salest.stream().map((EmergencyContactInfo)->modelMapper.map(EmergencyContactInfo, EmergencyContactInfoDto.class)).collect(Collectors.toList());
		return salesdt;
	}
}
