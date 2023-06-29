package com.ahom.hrms.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ahom.hrms.Response.ResponseHandler;
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

import javax.validation.Valid;

@RestController
@CrossOrigin 
@RequestMapping("/shiftManagement")
public class AddShiftController {


	private static final String AddShiftDto = null;
	@Autowired
	AddShiftServiceimpl addShiftService;


	@PostMapping("/addShift")
	public ResponseEntity<Object> saveAddShift(@Valid @RequestBody AddShiftDto addShiftDto) {
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,addShiftService.saveAddShift(addShiftDto));
	}


	@GetMapping("/viewShift")
	public ResponseEntity<Object> getAddShift() {
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,addShiftService.getAllAddShift());
	}


	@GetMapping("/viewEmployee/{userName}")
	public List<ShiftManagement> getAddShiftById(@PathVariable("userName") String userName) {
		return addShiftService.addShiftById(userName);
	}


	@DeleteMapping("/addShift/{id}")
	public ResponseEntity<Object> deleteaddShift(@PathVariable("id") String id){
		return ResponseHandler.responseBuilder("Deleted Successfully",HttpStatus.OK,addShiftService.deleteAddShift(id));
	}

	//Update
	@PutMapping("/update/{Id}")
	public ResponseEntity<Object> updateaddShift(@Valid @RequestBody ShiftManagement shiftManagement,@PathVariable("Id") String id ) {
		return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,addShiftService.updateAddshift(shiftManagement,id));
	}

//	@GetMapping("/time/{country}")
//	public String getTimeForCountry(@PathVariable String country) {
//		String timeZone = getTimeZoneForCountry(country);
//		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(timeZone));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formattedDateTime = localDateTime.format(formatter);
//		return "Current time in " + country + " is " + formattedDateTime;
//	}

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

