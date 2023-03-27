package com.ahom.hrms.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;	
	private String selectEmployee;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String inTime;
	private String outTime;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
	
	@Override
	public String toString() {
		return "Attendance [Id=" + Id + ", selectEmployee=" + selectEmployee + ", date=" + date + ", inTime=" + inTime
				+ ", outTime=" + outTime + "]";
	}

	
}