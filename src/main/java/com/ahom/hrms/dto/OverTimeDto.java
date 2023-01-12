package com.ahom.hrms.dto;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class OverTimeDto {
	
	private int Id;
	private String selectEmployee;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String startTime;
	private String endTime;
	private String description;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSelectEmployee() {
		return selectEmployee;
	}
	public void setSelectEmployee(String selectEmployee) {
		this.selectEmployee = selectEmployee;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
