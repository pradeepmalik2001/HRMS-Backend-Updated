package com.ahom.hrms.serviceimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.ahom.hrms.Repository.OverTimeRepository;
import com.ahom.hrms.entities.OverTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<OverTime> gteOt(String month, String userName) {
		DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
		Date startDate, endDate;
		try {
			month = month.toUpperCase();
			startDate = dateFormat.parse(month + " " + Calendar.getInstance().get(Calendar.YEAR));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();

		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		List<OverTime> list = overRepository.findByUserNameAndDateRange(startDate, endDate, userName);
		if (list.isEmpty())
			{
				throw new RuntimeException("Data for "+userName+" is not found");
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



/**
 * --------------------- Method to calculate the overtime done--------------------
 */
	@Override
	public int getByUserNameAndMonth(String month, String userName) {
		DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
		Date startDate, endDate;
		try {
			month = month.toUpperCase();
			startDate = dateFormat.parse(month + " " + Calendar.getInstance().get(Calendar.YEAR));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();

		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		List<OverTime> list = overRepository.findByUserNameAndDateRange(startDate, endDate, userName);
		List<Duration> timeDifferences = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		for (OverTime overTime : list) {
			LocalTime startTime = LocalTime.parse(overTime.getStartTime(), formatter);
			LocalTime endTime = LocalTime.parse(overTime.getEndTime(), formatter);

			Duration duration = Duration.between(startTime, endTime);
			timeDifferences.add(duration);
		}
		System.out.println("timeDifference : "+timeDifferences);

		long totalDurationMinutes = 0;

		for (Duration duration : timeDifferences) {
			totalDurationMinutes += duration.toMinutes();
		}

		int totalDuration = (int) totalDurationMinutes;
		System.out.println("totalDuration : "+totalDuration);
		return totalDuration;
	}

	@Override
	public List<OverTime> findByUserName(String userName) {
		return overRepository.findByUserName(userName);
	}
}
