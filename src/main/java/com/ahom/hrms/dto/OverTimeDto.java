package com.ahom.hrms.dto;


import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OverTimeDto {
	
	private int Id;

	@NotEmpty(message = "Employee Name can`t be Empty")
	private String selectEmployee;

	@Temporal(TemporalType.DATE)
	private Date date;

	@NotEmpty(message = "Start Time is Mandatory")
	private String startTime;

	@NotEmpty(message = "End Time is Mandatory")
	private String endTime;

	@NotEmpty(message = "Write Something Here")
	private String description;
	@NotEmpty
	private String userName;

	@NotEmpty(message = "Overtime rate can`t be empty")
	private double rate;

}
