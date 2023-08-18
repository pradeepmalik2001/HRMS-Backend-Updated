package com.ahom.hrms.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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


	public AddHolidayDto SaveAddHolidayDetail(AddHolidayDto addHolidayDto) throws ParseException {
		 AddHoliday addHolidays=addHolidayRepository.findByHolidayNameAndFromDateAndToDate
				(addHolidayDto.getHolidayName(),
						addHolidayDto.getFromDate(),
						addHolidayDto.getToDate());
		LocalDate startDate1= LocalDate.parse(addHolidayDto.getFromDate());
		LocalDate endDate1=LocalDate.parse(addHolidayDto.getToDate());
		 while (!startDate1.isAfter(endDate1)) {
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String startDateString = addHolidayDto.getFromDate();
			 String endDateString = addHolidayDto.getToDate();
			 Date startDate = formatter.parse(startDateString);
			 Date endDate = formatter.parse(endDateString);

			 if (Objects.isNull(addHolidays)) {
				 if (!endDate.before(startDate)) {
					 addHolidayDto.setDate(startDate1);
					 addHolidayRepository.save(AddHolidayDtotoAddHoliday(addHolidayDto));
					 startDate1=startDate1.plusDays(1);
				 } else {
					 throw new CustomException("From date should be of present day or after of ToDate");
				 }
			 } else {
				 throw new RuntimeException("Data Already Exist");
			 }
		 }

		return addHolidayDto;

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
			throw new RuntimeException("No Holiday Present");
		}
		return addHoliday;
	}

	public AddHoliday updateLeaveDetail(AddHolidayDto addHolidayDto ,int id) {


		AddHoliday addHolidayDto1=addHolidayRepository.findById(id).orElse(null);
		if(addHolidayDto1!=null)
		{
			addHolidayDto1.setHolidayName(addHolidayDto.getHolidayName());
			addHolidayDto1.setHolidayType(addHolidayDto.getHolidayType());
			addHolidayDto1.setFromDate(addHolidayDto.getFromDate());
			addHolidayDto1.setToDate(addHolidayDto.getToDate());
			addHolidayRepository.saveAndFlush(addHolidayDto1);
		}
		else {
			throw new RuntimeException("Holiday for Id : "+id+" is Not Found");
		}
		return addHolidayDto1;

	}

	public AddHoliday AddHolidayDtotoAddHoliday(AddHolidayDto addHolidayDto) {
		return this.modelMapper.map(addHolidayDto, AddHoliday.class);
	}

	public AddHolidayDto addHolidaytoAddHolidayDto(AddHoliday title) {
		return this.modelMapper.map(title, AddHolidayDto.class);
	}
}