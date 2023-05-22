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


	public Object SaveAddHolidayDetail(AddHolidayDto addHolidayDto)
	{
		 AddHoliday addHolidays=addHolidayRepository.findByHolidayNameAndFromDateAndToDate
				(addHolidayDto.getHolidayName(),
						addHolidayDto.getFromDate(),
						addHolidayDto.getToDate());

		 if(!Objects.isNull(addHolidays))

		{
			throw  new CustomException("Data Already Exist");

		}
		else
		{
			 return addHolidayRepository.save(AddHolidayDtotoAddHoliday(addHolidayDto));

		}

	}

	public List<AddHolidayDto>getAllLeaveDetail(){
		List<AddHoliday>jobTitles=this.addHolidayRepository.findAll();
		return jobTitles.stream().map(this::addHolidaytoAddHolidayDto).collect(Collectors.toList());
	}

	public AddHoliday deleteLaeveDetail(int id) {
		AddHoliday addHoliday=addHolidayRepository.findById(id).orElse(null);
		if (addHoliday!=null){
			addHolidayRepository.deleteById(id);

		}else {
			throw new RuntimeException("No Particular holiday present");
		}

		return addHoliday;
	}

	public Object updateLeaveDetail(AddHolidayDto addHolidayDto , int id) {

		AddHoliday addHolidayDto1=addHolidayRepository.findById(id).orElse(null);

		addHolidayDto1.setHolidayName(addHolidayDto.getHolidayName());
		addHolidayDto1.setHolidayType(addHolidayDto.getHolidayType());
		addHolidayDto1.setFromDate(addHolidayDto.getFromDate());
		addHolidayDto1.setToDate(addHolidayDto.getToDate());

		return addHolidayRepository.saveAndFlush(addHolidayDto1);

	}

	public AddHoliday AddHolidayDtotoAddHoliday(AddHolidayDto addHolidayDto) {
		AddHoliday addholiday=this.modelMapper.map(addHolidayDto, AddHoliday.class);
		return addholiday;
	}

	public AddHolidayDto addHolidaytoAddHolidayDto(AddHoliday title) {
		AddHolidayDto addjobtitledto=this.modelMapper.map(title, AddHolidayDto.class);
		return addjobtitledto;
	}
}