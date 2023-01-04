package com.ahom.hrms.dto;

import com.ahom.hrms.entities.PayRoll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAllowancesDto {
	private int Id;
	private int employeeId;
	private String Type;
	private String ammount;
	private String  effectiveDate;
	private String  dateCreated;



}




