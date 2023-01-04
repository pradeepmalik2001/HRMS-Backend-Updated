package com.ahom.hrms.serviceimpl;


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
	    public void saveAddShift(AddShiftDto addShiftDto)
	    {
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
	    	abc.setShiftName(shiftManagement.getShiftName());
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
	    public AddShiftDto addShiftById(Integer id)
	    {
	       ShiftManagement addShift = this.addShiftRepo.findById(id).get();
	        // Optional<Employee> byId = employeeReposatory.findById(employeeId);
	        return this.addShiftToAddShiftDto(addShift);

	    }


	    public ShiftManagement addShiftDtoToAddShift(AddShiftDto addShiftDto)
	    {
	        ShiftManagement addShift=new ShiftManagement();

	        addShift.setId(addShiftDto.getId());
	        addShift.setDate(addShiftDto.getDate());
	        addShift.setShiftName(addShiftDto.getShiftName());
	        addShift.setEmployee(addShiftDto.getEmployee());
	        return addShift;
	    }

	    public AddShiftDto addShiftToAddShiftDto(ShiftManagement addShift)
	    {
	    	AddShiftDto  addShiftDto = new AddShiftDto ();

	        addShiftDto.setId(addShift.getId());;
	        addShiftDto.setDate(addShift.getDate());
	        addShiftDto.setEmployee(addShift.getEmployee());
	        addShiftDto.setShiftName(addShift.getShiftName());

	        return addShiftDto;
	    }

	

}
