package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmergencyContactInfoDto
{
	private String id;
	@NotEmpty(message = "PLease Enter Mobile Number")
	@Size(min = 10,max = 10)
	private String emergencyContactMobile;

	@NotEmpty(message = "Please Enter Contact Name")
	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,30}",message = "Only alphabets")
	private String emergencyContactName;

	@NotEmpty(message = "Please Enter Your Email")
	@Email
	private String emergencyContactEmail;

	@NotEmpty(message = "Address can`t be empty")
	private String emergencyContactAddress;

	@NotEmpty(message = "Please Enter Your Name")
	private String employeeName;
	//private BasicEmployeeDto basicEmployeeDto;

}
