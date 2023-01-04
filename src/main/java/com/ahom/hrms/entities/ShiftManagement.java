package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shift_management")
public class ShiftManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String shiftName;
	private String employee;
	private String date;
	
	

	
	
	

	
}