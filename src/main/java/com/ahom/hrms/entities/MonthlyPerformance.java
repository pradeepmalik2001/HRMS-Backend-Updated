package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPerformance {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
