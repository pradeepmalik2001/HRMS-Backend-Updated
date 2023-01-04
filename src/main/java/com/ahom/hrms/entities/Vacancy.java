package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vacancy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String hiringManager;
	private String jobTitle;
	private String vacancyName;
	private int numberOfPosition;
	private String jobLocation;
	private String jobDescription;
	private boolean active;
	

}
