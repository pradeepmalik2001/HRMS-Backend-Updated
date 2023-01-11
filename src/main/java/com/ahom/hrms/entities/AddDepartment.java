package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
@Table(name = "add_department")
public class AddDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String departmentName;
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
