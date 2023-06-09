package com.ahom.hrms.dto;

import com.ahom.hrms.entities.UserMaster;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AttendanceDto {



	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty
	private String selectEmployee;
	@Temporal(TemporalType.DATE)
	private Date date;
	@NotEmpty
	private String inTime;
	@NotEmpty
	private String outTime;
	@NotEmpty
	private String status;
	@NotEmpty
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	private UserMaster userMaster;
//
//
//	public UserMaster getUserMaster() {
//		return userMaster;
//	}
//
//	public void setUserMaster(UserMaster userMaster) {
//		this.userMaster = userMaster;
//	}

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
