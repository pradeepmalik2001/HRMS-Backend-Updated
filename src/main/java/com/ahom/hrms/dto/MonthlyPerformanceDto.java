package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPerformanceDto {
	

	private int employeeId;
	private int reportAuth;
	private String name;
	private String department;
	private String designation;
	private String dateOfJoining;
	private String reportingMonth;
	private String reportingTime;
	private String description;
	private String status;

}
