package com.ahom.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.LeaveTypeDto;

import com.ahom.hrms.serviceimpl.LeaveTypeServiceImpl;
@RestController
@CrossOrigin
@RequestMapping("/leave")
public class LeaveTypeController {
	@Autowired
	LeaveTypeServiceImpl leaveTypeService;
	@PostMapping("/LeaveType")
	public ResponseEntity<LeaveTypeDto>SaveLeaveTypeDetail(@RequestBody LeaveTypeDto leaveTypeDto){
		leaveTypeService.SaveLeaveTypeDetail(leaveTypeDto);
		return new ResponseEntity<>(leaveTypeDto,HttpStatus.CREATED);
	 }
	@GetMapping("/LeaveType")
	public List<LeaveTypeDto> getAll(){
		List<LeaveTypeDto>alljob=leaveTypeService.getAllLeaveDetail();
		return alljob; 
	}
	@DeleteMapping("/leavetype/{deletei}")
	public void delete(@PathVariable ("deletei")int i) {
	leaveTypeService.deleteLaeveDetail(i);
	
} 

	//@PutMapping("/edit")
	//public ResponseEntity<LeaveTypeDto>updateallEntity(@RequestBody LeaveTypeDto leaveTypeDto){
	//leaveTypeService.updateLeaveType(leaveTypeDto);
	//	return new ResponseEntity<>(leaveTypeDto,HttpStatus.CREATED);	

}