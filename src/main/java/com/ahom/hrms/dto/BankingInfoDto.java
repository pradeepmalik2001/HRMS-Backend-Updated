package com.ahom.hrms.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BankingInfoDto {
	@NotEmpty
	private String id;

	@NotEmpty(message = "Please Enter Account Number")
	@Column(unique = true)
	private String bankAccountNo;

	@NotEmpty(message = "Please Enter Bank Name")
	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,30}",message = "only alphabets")
	private String bankName;

	@NotEmpty(message = "Please Enter Bank Branch")
	private String bankBranch;

	@NotEmpty(message = "Please Enter IFSC Code")
	@Pattern(regexp = "[A-Z]{4}[A-Z0-9]{7}",message = "IFSC Code should be in format HDFC090909")
	private String ifscCode;

	@NotEmpty(message = "Please Enter Your Name")
	private String name;


	private double basicSalary;
	private double grossSalary;
//	private BasicEmployee basicEmployee1;



}
