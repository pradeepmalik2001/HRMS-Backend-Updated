package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.LeaveTypeRepository;
import com.ahom.hrms.entities.LeaveType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.ahom.hrms.dto.LeaveTypeDto;


import com.ahom.hrms.service.LeaveTypeService;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository leaveTypeRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public void SaveLeaveTypeDetail(LeaveTypeDto leaveTypeDto) {
		leaveTypeRepository.save(LeaveTypeDtotoLeaveType(leaveTypeDto));
		
}
	
	public List<LeaveTypeDto> getAllLeaveDetail(){
		List<LeaveType>jobTitles=this.leaveTypeRepository.findAll();
		List<LeaveTypeDto>jobTitleToList=jobTitles.stream().map(title->this.leaveTypetoLeaveTypeDto(title)).collect(Collectors.toList());
		return jobTitleToList;
	}
//public LeaveType updateLeaveType(LeaveType leaveType) {
///		 LeaveType l=leaveTypeRepository.save(leaveType);
//		 
////		 return (l);
////	 }
	
	public void deleteLaeveDetail(int i) {
		leaveTypeRepository.deleteById(i);
	}
	public LeaveType LeaveTypeDtotoLeaveType(LeaveTypeDto leaveTypeDto) {
		LeaveType addholiday=this.modelMapper.map(leaveTypeDto, LeaveType.class);
//		addjobtitle.setId(addJobTitleDto.getId());
//		addjobtitle.setjobTitle(addJobTitleDto.getJobTitle());
		return addholiday;
	}
	public LeaveTypeDto leaveTypetoLeaveTypeDto(LeaveType leaveType) {
		LeaveTypeDto addjobtitledto=this.modelMapper.map(leaveType, LeaveTypeDto.class);
//		addjobtitledto.setId(addjobtitle.getId());
//		addjobtitledto.setJobTitle(addjobtitle.getjobTitle());
		return addjobtitledto;
	}
}