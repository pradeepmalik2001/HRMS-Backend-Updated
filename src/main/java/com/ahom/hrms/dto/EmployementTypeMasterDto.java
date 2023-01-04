package com.ahom.hrms.dto;

public class EmployementTypeMasterDto {
	
	private String employmentType;
	private String description;
	
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

	public EmployementTypeMasterDto(String employmentType, String description) {
		super();
		this.employmentType = employmentType;
		this.description = description;
	}
	

}
