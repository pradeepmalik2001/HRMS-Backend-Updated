package com.ahom.hrms.controller;


import java.util.List;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.service.AddHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.AddHolidayDto;
import com.ahom.hrms.serviceimpl.AddHolidayServiceImpl;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/holiday")
public class AddHolidayController {

@Autowired
AddHolidayServiceImpl addHolidayService;
@PostMapping("/leaveDetail")
public ResponseEntity<Object>SaveAddHolidayDetail(@Valid @RequestBody AddHolidayDto addHolidayDto){
	return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.CREATED,addHolidayService.SaveAddHolidayDetail(addHolidayDto));
}
@GetMapping("/leaveDetails")
public ResponseEntity<Object> getAllJob(){
	return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,addHolidayService.getAllLeaveDetail());
}
@DeleteMapping("/leaveDetail/{deletei}")
	public ResponseEntity<Object> delete(@PathVariable ("deletei")int i)
{
	return ResponseHandler.responseBuilder("Deleted Successfully",HttpStatus.OK,addHolidayService.deleteLaeveDetail(i));
} 
@PutMapping("/edit/{id}")
public ResponseEntity<Object>updateall(@RequestBody AddHolidayDto addHolidayDto,@PathVariable("id") int id){
return ResponseHandler.responseBuilder("Updated Successfully",HttpStatus.OK,addHolidayService.updateLeaveDetail(addHolidayDto,id));
}

}
