package com.ahom.hrms.serviceimpl;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.ahom.hrms.Repository.AddShiftRepo;
import com.ahom.hrms.service.AddShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.AddShiftDto;
import com.ahom.hrms.entities.ShiftManagement;


@Service
public class AddShiftServiceimpl implements AddShiftService {

	@Autowired
	AddShiftRepo addShiftRepo;


        @Override
		public void saveAddShift(AddShiftDto addShiftDto) {

			addShiftRepo.save(addShiftDtoToAddShift(addShiftDto));
		}

	@Override
	public List<AddShiftDto> getAllAddShift(){

		List<ShiftManagement> listAddShifts= this.addShiftRepo.findAll();

		List<AddShiftDto> addShiftDtoList = listAddShifts.stream().map(emp -> this.addShiftToAddShiftDto(emp)).collect(Collectors.toList());

		//employeeReposatory.findAll().forEach(l1->listEmployee.add(l1));

		return addShiftDtoList;
	}


	// delete
	@Override
	public void deleteAddShift(int Id){

		addShiftRepo.deleteById(Id);

	}
	//put

	@Override
	public void updateAddshift(ShiftManagement shiftManagement,int id) {
		//List<ShiftManagement> listAddShifts1= this.addShiftRepo.findAll();

		//listAddShifts1=listAddShifts1.stream().map(b->{
		ShiftManagement abc = addShiftRepo.findById(id).get();
		abc.setCountry(shiftManagement.getCountry());
		abc.setEmployee(shiftManagement.getEmployee());
		abc.setDate(shiftManagement.getDate());
//	    		if(abc.getId()==id)
//	    		{
//	    			abc.setShiftName(shiftManagement.getShiftName());
//	    			abc.setEmployee(shiftManagement.getEmployee());
//	    			abc.setDate(shiftManagement.getDate());
//	   
//	    		}
//	    		return abc;
//	    	}).collect(Collectors.toList());
		addShiftRepo.save(abc);
	}

//	    public AddShiftDto updateAddShift(AddShiftDto addShiftDto)
//	    {
//	    addShiftRepo.save(addShiftDtoToAddShift(addShiftDto));
//	    return addShiftDto;
//
//	    }

	@Override
	public AddShiftDto addShiftById(Integer id) {
		ShiftManagement addShift = this.addShiftRepo.findById(id).get();
		// Optional<Employee> byId = employeeReposatory.findById(employeeId);
		return this.addShiftToAddShiftDto(addShift);

	}


	public ShiftManagement addShiftDtoToAddShift(AddShiftDto addShiftDto) {
		ShiftManagement addShift=new ShiftManagement();

		String timeZone = getTimeZoneForCountry(addShiftDto.getCountry());
		LocalDateTime localDateTime=LocalDateTime.now(ZoneId.of(timeZone));
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedDateTime=localDateTime.format(formatter);


		addShift.setStartTime(addShiftDto.getStartTime());
		addShift.setEndTime(addShiftDto.getEndTime());
		addShift.setId(addShiftDto.getId());
		addShift.setDate(formattedDateTime);
		addShift.setCountry(addShiftDto.getCountry());
		addShift.setEmployee(addShiftDto.getEmployee());
		return addShift;
	}

	public AddShiftDto addShiftToAddShiftDto(ShiftManagement addShift) {
		AddShiftDto  addShiftDto = new AddShiftDto ();


		addShiftDto.setId(addShift.getId());;
		addShiftDto.setDate(addShift.getDate());
		addShiftDto.setEmployee(addShift.getEmployee());
		addShiftDto.setCountry(addShift.getCountry());
		addShiftDto.setStartTime(addShift.getStartTime());
		addShiftDto.setEndTime(addShift.getEndTime());

		return addShiftDto;
	}

	private String getTimeZoneForCountry(String country) {
		String timeZone;
		switch (country) {
			case "USA":
				timeZone = "America/New_York";
				break;
			case "India":
				timeZone = "Asia/Kolkata";
				break;
			case "Japan":
				timeZone = "Asia/Tokyo";
				break;
			// add more cases for other countries
			default:
				timeZone = "UTC";
				break;
		}
		return timeZone;
	}

}
