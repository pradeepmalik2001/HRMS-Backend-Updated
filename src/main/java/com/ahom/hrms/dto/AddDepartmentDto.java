package com.ahom.hrms.dto;

public class AddDepartmentDto {

	private String departmentName;
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
