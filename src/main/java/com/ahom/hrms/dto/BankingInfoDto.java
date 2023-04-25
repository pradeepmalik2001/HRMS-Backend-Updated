package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.Data;

@Data
public class BankingInfoDto {
	private int employeeId;
	private String bankAccountNo;
	private String bankName;
	private String bankBranch;
	private String ifscCode;
	private String name;


	private double basicSalary;
	private double grossSalary;
//	private BasicEmployee basicEmployee1;



}
