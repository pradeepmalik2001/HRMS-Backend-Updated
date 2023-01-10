package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.serviceimpl.AddShiftServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.AddShiftDto;
import com.ahom.hrms.entities.ShiftManagement;

@RestController
@CrossOrigin 
@RequestMapping("/shiftmanagement")
public class AddShiftController {
	

	    private static final String AddShiftDto = null;
		@Autowired
		AddShiftServiceimpl addShiftService;
	    
//	    @Autowired
//		private AddShiftDto addShiftDto;

	    @PostMapping("/addshift")
	    public ResponseEntity<AddShiftDto> saveAddShift(@RequestBody AddShiftDto addShiftDto)
	    {
	        addShiftService.saveAddShift(addShiftDto);

	        return new ResponseEntity<>(addShiftDto, HttpStatus.CREATED);
	    }


	    @GetMapping("/viewshift")
	    public List<AddShiftDto> getAddShift()
	    {
	        List<AddShiftDto> allEmployee = addShiftService.getAllAddShift();

	        return allEmployee;

	        //return allEmployee;
	    }


	    @GetMapping("/viewemployee/{Id}")
	    public AddShiftDto getAddShiftById(@PathVariable("Id") int Id)
	    {
	        return addShiftService.addShiftById(Id);
	    }


	    @DeleteMapping("/addshift/{Id}")
	    public void deleteaddShift(@PathVariable("Id") int Id){
	        addShiftService.deleteAddShift(Id);
	    }
	    
	    //Update
	    @PutMapping("/update/{Id}")
	    public ShiftManagement updateaddShift(@RequestBody ShiftManagement shiftManagement,@PathVariable("Id") int id ) {
	    	this.addShiftService.updateAddshift(shiftManagement,id);
	    	return shiftManagement;
	 }

	}


