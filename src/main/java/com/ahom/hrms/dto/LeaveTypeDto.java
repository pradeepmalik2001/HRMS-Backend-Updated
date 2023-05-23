package com.ahom.hrms.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LeaveTypeDto
{
	private int id;

	@NotEmpty(message="LeaveType is Mandatory ")
	private String leaveType;

	@NotEmpty(message="Description is Mandatory")
	private String description;

}
