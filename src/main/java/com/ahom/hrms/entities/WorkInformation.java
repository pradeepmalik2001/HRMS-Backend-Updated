package com.ahom.hrms.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "work_info")
public class WorkInformation {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String workId;
	private String employeeName;
	private String employmentType;
	private String officeBranch;

	private String gender;
	private String bloodGroup;
	private String employeeType;
	private String address;
	private String description;
	
	
@OneToOne(targetEntity = BasicEmployee.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private BasicEmployee basicEmployee;

	
	
}
