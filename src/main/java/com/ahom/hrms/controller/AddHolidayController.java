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
	public ResponseEntity<Object> SaveAddHolidayDetail(@Valid @RequestBody AddHolidayDto addHolidayDto) {
		return ResponseHandler.responseBuilder("Holiday saved successfully", HttpStatus.CREATED
				, addHolidayService.SaveAddHolidayDetail(addHolidayDto));
	}

	@GetMapping("/leaveDetails")
	public List<AddHolidayDto> getAllJob() {
		List<AddHolidayDto> allJob = addHolidayService.getAllLeaveDetail();
		return allJob;
	}

	@DeleteMapping("/leaveDetail/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		return ResponseHandler.responseBuilder("Designation for ID:" + id + " " + "deleted successfully",
				HttpStatus.OK, addHolidayService.deleteLaeveDetail(id));

	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Object> updateall(@RequestBody AddHolidayDto addHolidayDto, @PathVariable("id") int id) {
		return ResponseHandler.responseBuilder("Holiday updated", HttpStatus.CREATED,
				addHolidayService.updateLeaveDetail(addHolidayDto, id));

	}
}
