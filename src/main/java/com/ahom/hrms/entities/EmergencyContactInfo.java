package com.ahom.hrms.entities;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String emergencyContactMobile;
	private String emergencyContactName;
	private String emergencyContactEmail;
	private String emergencyContactAddress;
		
	@OneToOne
	private BasicEmployee basicEmployee;
}
