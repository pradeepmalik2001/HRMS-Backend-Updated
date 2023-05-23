package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingFeedbackDto
{
	private int id;

	@NotEmpty(message = "Training Name can`t be Empty")
	private String trainingName;

	@NotEmpty(message = "Employee Name can`t be empty")
	private String employeeName;

	@NotEmpty(message = "Write Something Here")
	private String feedback;
}
