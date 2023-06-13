package com.ahom.hrms.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emergency_contact_info")
public class EmergencyContactInfo {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@NotEmpty(message = "PLease Enter Mobile Number")
	@Pattern(regexp = "[0-9]{10,12}",message = "Contact number should be in numeric form")
	private String emergencyContactMobile;

	@NotEmpty(message = "Please Enter Contact Name")
	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,30}",message = "Only alphabets")
	private String emergencyContactName;

	@NotEmpty(message = "Please Enter Your Email")
	@Email
	private String emergencyContactEmail;

	@NotEmpty(message = "Address can`t be empty")
	private String emergencyContactAddress;

	@NotEmpty(message = "Please Enter Your Name")
	private String employeeName;
		
	@OneToOne(targetEntity = BasicEmployee.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private BasicEmployee basicEmployee;
}
