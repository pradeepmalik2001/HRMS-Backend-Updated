package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTrainingDto
{
	@NotEmpty(message = "Id can`t be null")
	private String id;

	@NotEmpty(message = "Event Name can`t be Empty")
	private String eventName;

	@NotEmpty(message = "Training name can`t be Empty")
	private String trainingName;

	@NotEmpty(message = "Employee Name can`t be Empty")
	private String employee;

}
