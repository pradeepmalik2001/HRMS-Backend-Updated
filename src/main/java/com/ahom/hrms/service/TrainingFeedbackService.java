package com.ahom.hrms.service;

import com.ahom.hrms.dto.TrainingFeedbackDto;
import com.ahom.hrms.entities.TrainingFeedback;

import java.util.List;

public interface TrainingFeedbackService {

	TrainingFeedbackDto saveTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto);

	TrainingFeedback trainingFeedbackDtoToTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto);

	TrainingFeedbackDto trainingFeedbackToTrainingFeedbackDto(TrainingFeedback trainingFeedback);

	List<TrainingFeedback> getAll();

}
