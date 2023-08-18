package com.ahom.hrms.controller;


import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.dto.AddHolidayDto;
import com.ahom.hrms.serviceimpl.AddHolidayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/holiday")
public class AddHolidayController {

@Autowired
AddHolidayServiceImpl addHolidayService;
@PostMapping("/leaveDetail")
public ResponseEntity<Object>SaveAddHolidayDetail(@Valid @RequestBody AddHolidayDto addHolidayDto) throws ParseException {
	return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.CREATED,addHolidayService.SaveAddHolidayDetail(addHolidayDto));
}
@GetMapping("/leaveDetails")
public ResponseEntity<Object> getAllJob(){
	return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,addHolidayService.getAllLeaveDetail());
}
@DeleteMapping("/leaveDetail/{id}")
	public ResponseEntity<Object> delete(@PathVariable ("id")int id)
{
	return ResponseHandler.responseBuilder("Deleted Successfully",HttpStatus.OK,addHolidayService.deleteLaeveDetail(id));
} 
@PutMapping("/edit/{id}")
public ResponseEntity<Object>updateall(@RequestBody AddHolidayDto addHolidayDto,@PathVariable("id") int id){
return ResponseHandler.responseBuilder("Updated Successfully",HttpStatus.OK,addHolidayService.updateLeaveDetail(addHolidayDto,id));
}

}
