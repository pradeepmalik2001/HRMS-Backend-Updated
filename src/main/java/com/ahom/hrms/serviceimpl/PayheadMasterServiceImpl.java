package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.PayheadMasterRepository;
import com.ahom.hrms.entities.PayheadMaster;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.LeaveTypeDto;
import com.ahom.hrms.dto.PayheadMasterDto;


import com.ahom.hrms.service.PayheadMasterService;

@Service
public class PayheadMasterServiceImpl implements PayheadMasterService {
@Autowired
PayheadMasterRepository payheadMasterRepository;
@Autowired
ModelMapper modelMapper;

public void SavePayheadMaster(PayheadMasterDto payheadMasterDto) {
	payheadMasterRepository.save(PayheadMasterDtotoPayheadMaster(payheadMasterDto));
}

public PayheadMaster PayheadMasterDtotoPayheadMaster(PayheadMasterDto payheadMasterDto) {
	PayheadMaster addholiday=this.modelMapper.map(payheadMasterDto, PayheadMaster.class);
	return addholiday;
}
public List<PayheadMasterDto> getpayheadMasterDetail(){
	List<PayheadMaster>jobTitles=this.payheadMasterRepository.findAll();
	List<PayheadMasterDto>jobTitleToList=jobTitles.stream().map(title->this.payheadMastertoPayheadMasterDto(title)).collect(Collectors.toList());
	return jobTitleToList; 
}

public void deletepayheadmasterDetail(int i) {
	payheadMasterRepository.deleteById(i);
}
public void updatpayheadMaster(PayheadMasterDto payheadMasterDto) {
	payheadMasterRepository.save(PayheadMasterDtotoPayheadMaster(payheadMasterDto));
}

public PayheadMasterDto payheadMastertoPayheadMasterDto(PayheadMaster payheadMaster) {
	PayheadMasterDto addjobtitledto=this.modelMapper.map(payheadMaster, PayheadMasterDto.class);

	return addjobtitledto;
}
}
