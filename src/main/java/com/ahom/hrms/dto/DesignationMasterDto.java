package com.ahom.hrms.dto;

public class DesignationMasterDto {
	
	private int Id;
	private String designationName;
	
	public DesignationMasterDto() {
		
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

	public DesignationMasterDto(int id, String designationName) {
		super();
		Id = id;
		this.designationName = designationName;
	}

}
