package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.TrainingFeedbackReposatory;
import com.ahom.hrms.entities.TrainingFeedback;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.dto.TrainingFeedbackDto;
import com.ahom.hrms.service.TrainingFeedbackService;

@Service
public class TrainingFeedbackServiceImpl implements TrainingFeedbackService {
	
	@Autowired
	TrainingFeedbackReposatory trainingFeedbackReposatory;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public TrainingFeedbackDto saveTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto) {
		trainingFeedbackReposatory.save(trainingFeedbackDtoToTrainingFeedback(trainingFeedbackDto));
		return trainingFeedbackDto;
}
	@Override
	public TrainingFeedback trainingFeedbackDtoToTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto) {
		TrainingFeedback trainingFeedback=this.modelMapper.map(trainingFeedbackDto,TrainingFeedback.class);
		return trainingFeedback;
	}
	@Override
	public TrainingFeedbackDto trainingFeedbackToTrainingFeedbackDto(TrainingFeedback trainingFeedback) {
		TrainingFeedbackDto trainingFeedbackDto=this.modelMapper.map(trainingFeedback,TrainingFeedbackDto.class);
		return trainingFeedbackDto;
	}
}