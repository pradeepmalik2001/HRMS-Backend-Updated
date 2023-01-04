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
public class Payroll_itemDto {

	private int id;
	private int payrollId;
	private int employeeId;
	private String present;
	private String absent;
	private String late;
	private String salary;
	private String allowanceAmount;
	private String allowancesType;
	private String deductionAmount;
	private String deductions;
	private String grossNet;
	private String dateCreated;


}