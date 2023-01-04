package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
@Table(name = "emergency_contact_info")
public class EmergencyContactInfo {
	
	@Id

	private String emergencyContactMobile;
	private String emergencyContactName;

	private String emergencyContactEmail;
	private String emergencyContactAddress;
	
	public EmergencyContactInfo() {
		
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}

	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
	}

	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}

	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}

	public String getEmergencyContactAddress() {
		return emergencyContactAddress;
	}

	public void setEmergencyContactAddress(String emergencyContactAddress) {
		this.emergencyContactAddress = emergencyContactAddress;
	}

	public EmergencyContactInfo(String emergencyContactName, String emergencyContactMobile,
			String emergencyContactEmail, String emergencyContactAddress) {
		super();
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactMobile = emergencyContactMobile;
		this.emergencyContactEmail = emergencyContactEmail;
		this.emergencyContactAddress = emergencyContactAddress;
	}
	
	

}
