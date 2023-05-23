package com.ahom.hrms.entities;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class EmployeeTraining
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Event Name can`t be Empty")
	private String eventName;

	@NotEmpty(message = "Training name can`t be Empty")
	private String trainingName;

	@NotEmpty(message = "Employee Name can`t be Empty")
	private String employee;

}
