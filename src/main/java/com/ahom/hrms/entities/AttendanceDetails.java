package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttendanceDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fromDate;
	private String toDate;
	private String selectEmployee;
	
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSelectEmployee() {
		return selectEmployee;
	}
	public void setSelectEmployee(String selectEmployee) {
		this.selectEmployee = selectEmployee;
	}
	
	
	@Override
	public String toString() {
		return "AttendanceDetails [fromDate=" + fromDate + ", toDate=" + toDate + ", selectEmployee=" + selectEmployee
				+ "]";
	}
	
	
	
	
	
	
	
}