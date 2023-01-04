package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

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
@CrossOrigin
@RestController
@RequestMapping("/CreateLeaveRequest")
public class CreateLeaveRequestController {
	
	@Autowired
	CreateLeaveRequestServiceImpl createLeaveRequestService;
	
	
	 @PostMapping("/post")
	    public ResponseEntity<CreateLeaveRequestDto> saveEmp(@RequestBody CreateLeaveRequestDto createLeaveRequestDto)
	    {
	        createLeaveRequestService.saveCreateLeaveRequest(createLeaveRequestDto);

	        return new ResponseEntity<>(createLeaveRequestDto, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/get")
	    public ResponseEntity<List<CreateLeaveRequestDto>> getCreateLeaveRequest()
	    {
	        List<CreateLeaveRequestDto> allCreateLeaveRequest = createLeaveRequestService.getAllCreateLeaveRequest();
       
	        return ResponseEntity.of(Optional.of(allCreateLeaveRequest));

	    }
	 
//	 @DeleteMapping("/createLeaveRequest/{createLeaveRequestId}")
//	    public void deleteEmp(@PathVariable("createLeaveRequestId") int id){
//	        createLeaveRequestService.deleteCreateLeaveRequest(id);
//	    }
	 
	 @PutMapping("/put")
	    public ResponseEntity<CreateLeaveRequestDto> updateEmp(@RequestBody CreateLeaveRequestDto createLeaveRequestDto)
	    {
	        createLeaveRequestService.updateCreateLeaveRequest(createLeaveRequestDto);
	        return new ResponseEntity<>(createLeaveRequestDto, HttpStatus.ACCEPTED);
	    }



}
