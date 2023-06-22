package com.ahom.hrms.serviceimpl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.OverTimeRepository;
import com.ahom.hrms.entities.OverTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.OverTimeDto;

import com.ahom.hrms.service.OverTimeService;

@Service
public class OverTimeServiceImpl implements OverTimeService {

	@Autowired
	OverTimeRepository overRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public OverTimeDto EmployeeSave(OverTimeDto overtimedto)
	{
		overRepository.save(OverTimeDtoToOverTime(overtimedto));
		return overtimedto;
	}
	
	@Override
	public List<OverTimeDto> Employeefetch()
	{
		List<OverTimeDto> EmployeeList=new ArrayList<OverTimeDto>();
		overRepository.findAll().forEach(li->EmployeeList.add(OverTimeToOverTimeDto(li)));
		return EmployeeList;
	}

	@Override
	public List<OverTime> gteOt(Date startdate, Date enddate, String name) {
		List<OverTime> list = overRepository.findByNameAndDateRange(startdate, enddate, name);
			if (list.isEmpty())
			{
				throw new RuntimeException("Data for "+name+" is not found");
			}
			else {
				return list;
			}
	}


	/** -------------Using DTO Class in OverTimeDtoToOverTime --------------------------*/
	
	public OverTime OverTimeDtoToOverTime(OverTimeDto overtimedto)
	{
		String startTime = overtimedto.getStartTime();
		String endTime = overtimedto.getEndTime();
		LocalTime parse = LocalTime.parse(startTime);
		LocalTime parse1 = LocalTime.parse(endTime);
		if (parse1.isAfter(parse)) {
			return this.modelMapper.map(overtimedto, OverTime.class);
		}else
			throw new RuntimeException("End time should be after then start time");
	}

	/** ------------Using DTO Class in OverTimeToOverTimeDto --------------------------*/
	
	 public OverTimeDto OverTimeToOverTimeDto(OverTime addOvertime)
	    {
			return this.modelMapper.map(addOvertime, OverTimeDto.class);
	    }	
}
