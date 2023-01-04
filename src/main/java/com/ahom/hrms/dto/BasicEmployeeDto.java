package com.ahom.hrms.dto;

public class BasicEmployeeDto {
	
	private int employeeId;
	private String employeeName;
	private String whichCompany;
	private String selectDepartment;
	private String designation;
	private String email;
	private String mobile;
	private String joiningDate;
	private String reportingTo;
	private String dob;
	private String workType;
	
	public BasicEmployeeDto() {
		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getWhichCompany() {
		return whichCompany;
	}

	public void setWhichCompany(String whichCompany) {
		this.whichCompany = whichCompany;
	}

	public String getSelectDepartment() {
		return selectDepartment;
	}

	public void setSelectDepartment(String selectDepartment) {
		this.selectDepartment = selectDepartment;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public BasicEmployeeDto(int employeeId, String employeeName, String whichCompany, String selectDepartment,
			String designation, String email, String mobile, String joiningDate, String reportingTo, String dob,
			String workType) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.whichCompany = whichCompany;
		this.selectDepartment = selectDepartment;
		this.designation = designation;
		this.email = email;
		this.mobile = mobile;
		this.joiningDate = joiningDate;
		this.reportingTo = reportingTo;
		this.dob = dob;
		this.workType = workType;
	}

	
}
