package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
@Table(name = "approval")
public class AddApproval {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userName;
	private String employeeName;
	private String approvalName;
	private String approvalCode;
	
	public AddApproval() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}


	public AddApproval(int id, String userName, String employeeName, String approvalName, String approvalCode) {
		this.id = id;
		this.userName = userName;
		this.employeeName = employeeName;
		this.approvalName = approvalName;
		this.approvalCode = approvalCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
