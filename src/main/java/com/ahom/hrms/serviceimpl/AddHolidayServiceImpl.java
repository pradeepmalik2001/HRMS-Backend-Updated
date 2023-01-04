package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.AddHolidayRepository;
import com.ahom.hrms.entities.AddHoliday;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ahom.hrms.dto.AddHolidayDto;

import com.ahom.hrms.service.AddHolidayService;

@Service
public class AddHolidayServiceImpl implements AddHolidayService{
	@Autowired
	AddHolidayRepository addHolidayRepository;
	@Autowired
	ModelMapper modelMapper;


	public void SaveAddHolidayDetail(AddHolidayDto addHolidayDto) {
		addHolidayRepository.save(AddHolidayDtotoAddHoliday(addHolidayDto));
	}

	public List<AddHolidayDto>getAllLeaveDetail(){
		List<AddHoliday>jobTitles=this.addHolidayRepository.findAll();
		List<AddHolidayDto>jobTitleToList=jobTitles.stream().map(title->this.addHolidaytoAddHolidayDto(title)).collect(Collectors.toList());
		return jobTitleToList;
	}

	public void deleteLaeveDetail(int i) {
		addHolidayRepository.deleteById(i);
	}

	public void updateLeaveDetail(AddHolidayDto addHolidayDto) {
		addHolidayRepository.save(AddHolidayDtotoAddHoliday(addHolidayDto));
	}

	public AddHoliday AddHolidayDtotoAddHoliday(AddHolidayDto addHolidayDto) {
		AddHoliday addholiday=this.modelMapper.map(addHolidayDto, AddHoliday.class);
		//	addjobtitle.setId(addJobTitleDto.getId());
		//	addjobtitle.setjobTitle(addJobTitleDto.getJobTitle());
		return addholiday;
	}
	public AddHolidayDto addHolidaytoAddHolidayDto(AddHoliday title) {
		AddHolidayDto addjobtitledto=this.modelMapper.map(title, AddHolidayDto.class);
		//	addjobtitledto.setId(addjobtitle.getId());
		//	addjobtitledto.setJobTitle(addjobtitle.getjobTitle());
		return addjobtitledto;
	}
}