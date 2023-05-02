package com.ahom.hrms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "add_department")
public class AddDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	@NotEmpty(message = "Department name can't be null")
	private String departmentName;
	@NotEmpty(message = "Description is null")
	private String description;
	
	public AddDepartment() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public AddDepartment(int id, int departmentId, String departmentName, String description) {
		this.id = id;
		this.departmentName = departmentName;
		this.description = description;
	}
}
