package com.ahom.hrms.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToOne(targetEntity = UserMaster.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_master_id")
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