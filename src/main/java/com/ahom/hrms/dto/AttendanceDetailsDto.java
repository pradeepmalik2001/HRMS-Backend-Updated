package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

public class AttendanceDetailsDto {
	

	private String fromDate;
	private String toDate;
	private String selectEmployee;
	private int empId;
	private BasicEmployee basicEmployee;

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

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public BasicEmployee getBasicEmployee() {
		return basicEmployee;
	}

	public void setBasicEmployee(BasicEmployee basicEmployee) {
		this.basicEmployee = basicEmployee;
	}
}
