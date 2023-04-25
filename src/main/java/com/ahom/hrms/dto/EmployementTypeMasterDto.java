package com.ahom.hrms.dto;

import javax.validation.constraints.NotEmpty;

public class EmployementTypeMasterDto {
	private  int id;

	@NotEmpty(message = "Employment Type is not empty")
	private String employmentType;
	@NotEmpty(message = "Write some description here")
	private String description;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployementTypeMasterDto() {
		
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployementTypeMasterDto(int id, String employmentType, String description) {
		this.id = id;
		this.employmentType = employmentType;
		this.description = description;
	}
}
