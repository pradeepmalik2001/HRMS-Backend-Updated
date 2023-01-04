package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
	
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String vacancy;
	private String candidate;
	private String time;
	private String date;
	private String method;
	private String status;
	private String comment;
}
