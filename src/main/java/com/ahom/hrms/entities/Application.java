package com.ahom.hrms.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
	private String fullName;
	@Column(name="email",nullable = false,unique = true)
	private String email;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String contact;
	private int yearOfExperince;
	private String locationJobApplied;
	private String vacancyType;
	private String date;
	private String exampleTextArea;
	private int careerPercentage;
	
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "application", fetch = FetchType.LAZY )
//	private ImageData resumeupload;
	private String imageUrl;


}
