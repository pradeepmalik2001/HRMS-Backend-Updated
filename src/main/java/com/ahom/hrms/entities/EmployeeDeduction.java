package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDeduction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private int deductionId;
	private String type;
	private double ammount;
	private String effectiveDate;
	private String dateCreated;

	@ManyToOne
	private PayRoll payRoll;

}