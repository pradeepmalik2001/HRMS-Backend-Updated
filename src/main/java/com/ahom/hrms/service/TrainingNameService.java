package com.ahom.hrms.service;

import com.ahom.hrms.dto.TrainingNameDto;
import com.ahom.hrms.entities.TrainingName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainingNameService {


    void saveTrainingName(TrainingNameDto trainingNameDto);

    TrainingName trainingNameDtoToTrainingName(TrainingNameDto trainingNameDto);

    TrainingNameDto trainingNameToTrainingNameDto(TrainingName trainingName);

    List<TrainingNameDto> getAll();
}
