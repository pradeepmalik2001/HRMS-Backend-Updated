package com.ahom.hrms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.PayheadMasterDto;
import com.ahom.hrms.serviceimpl.PayheadMasterServiceImpl;
@RestController
@CrossOrigin
@RequestMapping("payHead")
public class PayheadMasterController {
@Autowired
PayheadMasterServiceImpl payheadMasterService;
@PostMapping("/PayheadMaster")
public ResponseEntity<PayheadMasterDto>SaveLeaveTypeDetail( @Valid @RequestBody PayheadMasterDto payheadMasterDto){
	payheadMasterService.SavePayheadMaster(payheadMasterDto);
	return new ResponseEntity<>(payheadMasterDto,HttpStatus.CREATED);
 }
@GetMapping("/PayheadMasterDetail")
public List<PayheadMasterDto>getAll(){
	List<PayheadMasterDto>all=payheadMasterService.getpayheadMasterDetail();
	return all;
	
}
@DeleteMapping("/deletepayheadmaster/{deletei}")
public void delete(@PathVariable("deletei") int i) {
	payheadMasterService.deletepayheadmasterDetail(i);
}
@PutMapping("/updatepayheadmMaster")
public ResponseEntity<PayheadMasterDto>updateall(@RequestBody PayheadMasterDto payheadMasterDto) {
	payheadMasterService.updatpayheadMaster(payheadMasterDto);
	return new ResponseEntity<>(payheadMasterDto,HttpStatus.CREATED);
}
}