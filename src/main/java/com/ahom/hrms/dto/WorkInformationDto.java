package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkInformationDto {
	private String employeeName;
	private String employmentType;
	private String officeBranch;
	private String employeeGrade;
	private String employeeGroup;
	
	private String insuranceAvail;
	private String gender;
	private String bloodGroup;
	private long incentive;
	private String employeeType;
	private int value;
	private String effectiveDate;
	private String pfAccountNo;
	private String esiNo;
	private String cinNo;
	private String leavingDate;
	private String address;
	private String description;

	//private BasicEmployee basicEmployee;
	
}
