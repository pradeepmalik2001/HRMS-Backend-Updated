package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.Data;

@Data
public class BankingInfoDto {
	
	private String bankAccountNo;
	private String bankName;
	private String bankBranch;
	private String ifscCode;
//	private String paymentType;
	private String name;
	private int employeeId;

	private double basicSalary;
	private double grossSalary;


//	private String pfAcc;
	private BasicEmployee basicEmployee1;



}
