package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.LeaveTypeDto;

import com.ahom.hrms.serviceimpl.LeaveTypeServiceImpl;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/leave")
public class LeaveTypeController
{

	@Autowired
	LeaveTypeServiceImpl leaveTypeService;

	@PostMapping("/leaveType")
	public ResponseEntity<Object>SaveLeaveTypeDetail(@Valid @RequestBody LeaveTypeDto leaveTypeDto){
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,leaveTypeService.SaveLeaveTypeDetail(leaveTypeDto));
	 }

	@GetMapping("/leaveType")
	public ResponseEntity<Object> getAll(){
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,leaveTypeService.getAllLeaveDetail());
	}

	@DeleteMapping("/leaveType/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		return ResponseHandler.responseBuilder("Deleted Successfully",HttpStatus.OK,leaveTypeService.deleteLaeveDetail(id));
	}

	@PutMapping("/leaveType/{id}")
	public ResponseEntity<Object> updateLeaveType(@RequestBody LeaveTypeDto leaveType,@PathVariable int id)
	{
		return ResponseHandler.responseBuilder("Updated Successfully",HttpStatus.OK,leaveTypeService.updateLeaveType(leaveType,id));
	}

}