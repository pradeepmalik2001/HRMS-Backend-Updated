package com.ahom.hrms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "designation_master")
public class DesignationMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	@Column(unique=true)
	private String designationName;
	
	public DesignationMaster() {
		
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public DesignationMaster(int id, String designationName) {
		super();
		Id = id;
		this.designationName = designationName;
	}

	
}
