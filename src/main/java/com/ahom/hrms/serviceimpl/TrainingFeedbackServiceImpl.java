package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.TrainingFeedbackReposatory;
import com.ahom.hrms.entities.TrainingFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.TrainingFeedbackDto;
import com.ahom.hrms.service.TrainingFeedbackService;

@Service
public class TrainingFeedbackServiceImpl implements TrainingFeedbackService {
	
	@Autowired
	TrainingFeedbackReposatory trainingFeedbackReposatory;
	
	@Override
	public void saveTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto) {
		trainingFeedbackReposatory.save(trainingFeedbackDtoToTrainingFeedback(trainingFeedbackDto));
		
}
	@Override
	public TrainingFeedback trainingFeedbackDtoToTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto) {
		TrainingFeedback trainingFeedback = new TrainingFeedback();
		
		trainingFeedback.setId(trainingFeedbackDto.getId());
		trainingFeedback.setFeedback(trainingFeedbackDto.getFeedback());
		return trainingFeedback;
	}
	@Override
	public TrainingFeedbackDto trainingFeedbackToTrainingFeedbackDto(TrainingFeedback trainingFeedback) {
		TrainingFeedbackDto trainingFeedbackDto = new TrainingFeedbackDto();
		
		trainingFeedbackDto.setId(trainingFeedback.getId());
		trainingFeedbackDto.setFeedback(trainingFeedback.getFeedback());
		return trainingFeedbackDto;
	}
}