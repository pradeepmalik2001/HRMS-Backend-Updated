package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkInformationDto {

	private int workId;
	@NotEmpty(message = "Employee name can not be null")
	private String employeeName;
	@NotEmpty(message = "Employment type can not be null")
	private String employmentType;
	@NotEmpty(message = "Branch required")
	private String officeBranch;
	@NotEmpty(message = "specify gender")
	private String gender;
	@NotEmpty(message = "Blood Group needed")
	private String bloodGroup;
	@NotEmpty(message = "Specify Employment type")
	private String employeeType;
	@NotEmpty(message = "Fill the address")
	private String address;
	@NotEmpty(message = "Description is empty")
	private String description;
	
}
