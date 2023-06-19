package com.ahom.hrms.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.CreateLeaveRequest;
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

import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.serviceimpl.CreateLeaveRequestServiceImpl;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/CreateLeaveRequest")
public class CreateLeaveRequestController {
	
	@Autowired
	CreateLeaveRequestServiceImpl createLeaveRequestService;
	
	
	 @PostMapping("/post")
	    public ResponseEntity<Object> saveEmp( @Valid @RequestBody CreateLeaveRequest createLeaveRequest) throws ParseException {
			return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,createLeaveRequestService.saveCreateLeaveRequest(createLeaveRequest));
	    }
	 
	 @GetMapping("/get")
	    public ResponseEntity<Object> getCreateLeaveRequest()
	    {
			return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,createLeaveRequestService.getAllCreateLeaveRequest());
	    }
	 
//	 @DeleteMapping("/createLeaveRequest/{createLeaveRequestId}")
//	    public void deleteEmp(@PathVariable("createLeaveRequestId") int id){
//	        createLeaveRequestService.deleteCreateLeaveRequest(id);
//	    }
	 
	 @PutMapping("/put/{id}")
	    public ResponseEntity<Object> updateEmp(@RequestBody CreateLeaveRequestDto createLeaveRequestDto,@PathVariable String id)
	    {
			return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,
					createLeaveRequestService.updateCreateLeaveRequest(createLeaveRequestDto,id));
	    }



}
