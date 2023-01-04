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
public class Deduction {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int Id;
	private double Deduction;
	private String Description;
	@ManyToOne
	private PayRoll payRoll;

	

}
