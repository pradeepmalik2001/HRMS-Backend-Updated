package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmergencyContactInfoDto
{
	private String id;
	@NotEmpty(message = "PLease Enter Mobile Number")
	private String emergencyContactMobile;

	@NotEmpty(message = "Please Enter Contact Name")
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
