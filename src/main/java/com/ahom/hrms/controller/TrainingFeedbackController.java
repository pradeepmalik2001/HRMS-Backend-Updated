package com.ahom.hrms.controller;

import com.ahom.hrms.serviceimpl.TrainingFeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.TrainingFeedbackDto;


@CrossOrigin
@RestController
public class TrainingFeedbackController {
	
	@Autowired
	TrainingFeedbackServiceImpl trainingFeedbackService;
	
	@PostMapping("/feedback")
	public ResponseEntity<TrainingFeedbackDto> saveTrainingFeedback(@RequestBody TrainingFeedbackDto trDto){
		trainingFeedbackService.saveTrainingFeedback(trDto);
		return new ResponseEntity<>(trDto,HttpStatus.CREATED);
	}
	

}
