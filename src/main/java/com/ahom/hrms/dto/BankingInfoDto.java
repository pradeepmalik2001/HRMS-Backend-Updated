package com.ahom.hrms.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
public class BankingInfoDto {
	private int employeeId;

	@NotEmpty(message = "Please Enter Account Number")
	@Column(unique = true)
	private String bankAccountNo;

	@NotEmpty(message = "Please Enter Bank Name")
	private String bankName;

	@NotEmpty(message = "Please Enter Bank Branch")
	private String bankBranch;

	@NotEmpty(message = "Please Enter IFSC Code")
	private String ifscCode;

	@NotEmpty(message = "Please Enter Your Name")
	private String name;


	private double basicSalary;
	private double grossSalary;
//	private BasicEmployee basicEmployee1;



}
