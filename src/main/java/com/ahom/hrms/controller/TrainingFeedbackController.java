package com.ahom.hrms.controller;

import com.ahom.hrms.serviceimpl.TrainingFeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ahom.hrms.dto.TrainingFeedbackDto;


@CrossOrigin
@RestController
@RequestMapping("/training")
public class TrainingFeedbackController {
	
	@Autowired
	TrainingFeedbackServiceImpl trainingFeedbackService;
	
	@PostMapping("/feedback")
	public ResponseEntity<TrainingFeedbackDto> saveTrainingFeedback(@RequestBody TrainingFeedbackDto trDto){
		trainingFeedbackService.saveTrainingFeedback(trDto);
		return new ResponseEntity<>(trDto,HttpStatus.CREATED);
	}
	

}
