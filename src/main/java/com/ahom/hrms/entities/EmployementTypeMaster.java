package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
@Table(name = "employment_type_master")
public class EmployementTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String employmentType;
	private String description;
	
	public EmployementTypeMaster() {
		
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

	public EmployementTypeMaster(String employmentType, String description) {
		super();
		this.employmentType = employmentType;
		this.description = description;
	}
	

}
