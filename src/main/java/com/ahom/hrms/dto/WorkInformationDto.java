package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkInformationDto {

	@NotBlank
	private String workId;
	@NotEmpty(message = "Employee name can not be null")
	private String employeeName;
	@NotEmpty(message = "Employment type can not be null")
	private String employmentType;
	@NotEmpty(message = "Branch required")
	private String officeBranch;

	private String gender;

	private String bloodGroup;
	@NotEmpty(message = "Fill the address")
	private String address;

	private String description;
	
}
