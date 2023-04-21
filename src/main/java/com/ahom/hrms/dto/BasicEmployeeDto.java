package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	private int ctc;
	private String pfnumber;
	private String panNumber;
	private String aadhaarNumber;

	private WorkInformationDto workInfoDto;
	
	

	
}
