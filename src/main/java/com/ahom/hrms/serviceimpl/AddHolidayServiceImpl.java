package com.ahom.hrms.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.AddHolidayRepository;
import com.ahom.hrms.entities.AddHoliday;
import com.ahom.hrms.exception.CustomException;
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


	public void SaveAddHolidayDetail(AddHolidayDto addHolidayDto)
	{
		 AddHoliday addHolidays=addHolidayRepository.findByHolidayNameAndFromDateAndToDate
				(addHolidayDto.getHolidayName(),
						addHolidayDto.getFromDate(),
						addHolidayDto.getToDate());
		if(Objects.isNull(addHolidays))
		{
			addHolidayRepository.save(AddHolidayDtotoAddHoliday(addHolidayDto));
		}
		else
		{
			throw  new CustomException("Data Already Exist");
		}
	}

	public List<AddHolidayDto>getAllLeaveDetail(){
		List<AddHoliday>jobTitles=this.addHolidayRepository.findAll();
		List<AddHolidayDto>jobTitleToList=jobTitles.stream().map(title->this.addHolidaytoAddHolidayDto(title)).collect(Collectors.toList());
		return jobTitleToList;
	}

	public void deleteLaeveDetail(int i) {
		AddHoliday addHoliday=addHolidayRepository.findById(i).orElse(null);
		if (addHoliday!=null){
			addHolidayRepository.deleteById(i);
			throw new CustomException("deleted successfully");
		}else {
			throw new CustomException("No Particular holiday present");
		}

	}

	public void updateLeaveDetail(AddHolidayDto addHolidayDto ,int id) {

		AddHoliday addHolidayDto1=addHolidayRepository.findById(id).orElse(null);

		addHolidayDto1.setHolidayName(addHolidayDto.getHolidayName());
//		addHolidayDto1.setId(addHolidayDto.getId());
		addHolidayDto1.setHolidayType(addHolidayDto.getHolidayType());
		addHolidayDto1.setFromDate(addHolidayDto.getFromDate());
		addHolidayDto1.setToDate(addHolidayDto.getToDate());

		addHolidayRepository.saveAndFlush(addHolidayDto1);
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