package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.AddApprovalDto;
import com.ahom.hrms.service.AddApprovalService;

@RestController
@CrossOrigin
public class AddApprovalController {

	@Autowired
	AddApprovalService addApprovalService;

	//save data
	@PostMapping("/saveapproval")
	public ResponseEntity<AddApprovalDto> saveApproval(@RequestBody AddApprovalDto addApprovalDto){
		addApprovalService.saveAddApproval(addApprovalDto);
		return new ResponseEntity<>(addApprovalDto, HttpStatus.CREATED);
	}

}
