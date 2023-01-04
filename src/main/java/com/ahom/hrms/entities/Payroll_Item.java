package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name=" Payroll_Item")
public class Payroll_Item {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
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
	@ManyToOne
	private PayRoll payRoll;

}