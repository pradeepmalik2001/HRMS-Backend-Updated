package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
@Table(name = "employment_type_master")
public class EmployementTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int id;
	private String employmentType;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public EmployementTypeMaster(int id, String employmentType, String description) {
		this.id = id;
		this.employmentType = employmentType;
		this.description = description;
	}
}
