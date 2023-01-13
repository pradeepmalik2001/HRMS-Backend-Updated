package com.ahom.hrms.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class AttendanceDto {

	
	private int Id;	
	private String selectEmployee;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String inTime;
	private String outTime;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
}
