package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmergencyContactInfoDto {
	
	private String emergencyContactName;
	private String emergencyContactMobile;
	private String emergencyContactEmail;
	private String emergencyContactAddress;
	private String employeeName;
	//private BasicEmployeeDto basicEmployeeDto;

}
