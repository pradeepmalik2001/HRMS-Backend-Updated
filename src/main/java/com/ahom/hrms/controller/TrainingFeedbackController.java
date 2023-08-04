package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.serviceimpl.TrainingFeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.TrainingFeedbackDto;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/training")
public class TrainingFeedbackController {
	
	@Autowired
	TrainingFeedbackServiceImpl trainingFeedbackService;
	
	@PostMapping("/feedback")
	public ResponseEntity<Object> saveTrainingFeedback(@Valid @RequestBody TrainingFeedbackDto trDto){
		return ResponseHandler.responseBuilder("Data Saved Successfully",HttpStatus.OK,trainingFeedbackService.saveTrainingFeedback(trDto));
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object>getAll(){
		return ResponseHandler.responseBuilder("fetched",HttpStatus.OK,trainingFeedbackService.getAll());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable int id)
	{
		return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,trainingFeedbackService.getById(id));
	}
	

}
