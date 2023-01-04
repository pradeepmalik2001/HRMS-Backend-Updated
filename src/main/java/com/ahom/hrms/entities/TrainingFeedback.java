package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TrainingFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String feedback;

//	@OneToOne(cascade = CascadeType.ALL)
//	private EmployeeTraining employeeTraining;


}
