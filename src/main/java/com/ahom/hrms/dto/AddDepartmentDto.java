package com.ahom.hrms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class AddDepartmentDto {

	@NotEmpty(message = "Department name can't be null")
	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,15}",message = "Only Alphabets Allowed")
	private String departmentName;
	@NotEmpty(message = "Description is null")
	private String description;
	
	public AddDepartmentDto() {
		
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddDepartmentDto(int departmentId, String departmentName, String description) {
		super();
		this.departmentName = departmentName;
		this.description = description;
	}

}
