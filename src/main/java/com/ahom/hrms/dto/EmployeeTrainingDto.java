package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTrainingDto
{
	private int id;

	@NotEmpty(message = "Event Name can`t be Empty")
	private String eventName;

	@NotEmpty(message = "Training name can`t be Empty")
	private String trainingName;

	@NotEmpty(message = "Employee Name can`t be Empty")
	private String employee;

}
