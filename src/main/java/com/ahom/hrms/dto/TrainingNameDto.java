package com.ahom.hrms.dto;

import javax.validation.constraints.NotEmpty;

public class TrainingNameDto {


    private int id;
    @NotEmpty(message = "Training Name can`t be Empty")
    private String trainingName;
    @NotEmpty(message = "Please Write Something Here")
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
