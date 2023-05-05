package com.ahom.hrms.dto;

import com.ahom.hrms.entities.UserMaster;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class 	AttendanceDto {

	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String selectEmployee;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String inTime;
	private String outTime;
	private String status;
	private UserMaster userMaster;

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
