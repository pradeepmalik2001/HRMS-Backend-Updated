package com.ahom.hrms.service;

import com.ahom.hrms.dto.TrainingFeedbackDto;
import com.ahom.hrms.entities.TrainingFeedback;

public interface TrainingFeedbackService {

	void saveTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto);

	TrainingFeedback trainingFeedbackDtoToTrainingFeedback(TrainingFeedbackDto trainingFeedbackDto);

	TrainingFeedbackDto trainingFeedbackToTrainingFeedbackDto(TrainingFeedback trainingFeedback);

}
