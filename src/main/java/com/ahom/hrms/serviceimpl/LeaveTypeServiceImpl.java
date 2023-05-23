package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.LeaveTypeRepository;
import com.ahom.hrms.dto.LeaveTypeDto;
import com.ahom.hrms.entities.LeaveType;
import com.ahom.hrms.service.LeaveTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository leaveTypeRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public LeaveTypeDto SaveLeaveTypeDetail(LeaveTypeDto leaveTypeDto) {
		leaveTypeRepository.save(LeaveTypeDtotoLeaveType(leaveTypeDto));
		return leaveTypeDto;
	}
	
	public List<LeaveTypeDto> getAllLeaveDetail(){
		List<LeaveType>jobTitles=this.leaveTypeRepository.findAll();
		List<LeaveTypeDto>jobTitleToList=jobTitles.stream().map(title->this.leaveTypetoLeaveTypeDto(title)).collect(Collectors.toList());
		return jobTitleToList;
	}

	
	public LeaveType deleteLaeveDetail(int id)
	{
		LeaveType leaveType=leaveTypeRepository.findById(id).orElse(null);
		if(leaveType!=null)
		{
			leaveTypeRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("Leave Type for Id : "+id+" is Not Found");
		}
		return leaveType;
	}

	@Override
	public LeaveType updateLeaveType(LeaveTypeDto leaveType, int id)
	{
		LeaveType leaveType1=this.leaveTypeRepository.findById(id).orElse(null);
		if(leaveType1!=null)
		{
			leaveType1.setLeaveType(leaveType.getLeaveType());
			leaveType1.setDescription(leaveType.getDescription());
			leaveTypeRepository.saveAndFlush(leaveType1);
		}
		else {
			throw new RuntimeException("Leave Type for Id : " + id + " is Not Found");
		}
		return leaveType1;
	}


	public LeaveType LeaveTypeDtotoLeaveType(LeaveTypeDto leaveTypeDto) {
		LeaveType addholiday=this.modelMapper.map(leaveTypeDto, LeaveType.class);
		return addholiday;
	}


	public LeaveTypeDto leaveTypetoLeaveTypeDto(LeaveType leaveType) {
		LeaveTypeDto addjobtitledto=this.modelMapper.map(leaveType, LeaveTypeDto.class);
		return addjobtitledto;
	}

}