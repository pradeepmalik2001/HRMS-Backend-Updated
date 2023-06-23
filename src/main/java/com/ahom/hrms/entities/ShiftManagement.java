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
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shift_management")
public class ShiftManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Country Name Can`t be Empty")
	private String country;

	@NotEmpty(message = "Employee Name Can`t be Empty")
	private String employee;

	private String date;

	@NotEmpty(message = "Start Time Can`t be Empty")
	private String startTime;

	@NotEmpty(message = "End Time Can`t be Empty")
	private String endTime;
}