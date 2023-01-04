package com.ahom.hrms.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAllowances {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int Id;
	private int employeeId;
	private String Type;
	private String ammount;
	private String effectiveDate;
	private String dateCreated;
	@ManyToOne
	private PayRoll payRoll;

}
