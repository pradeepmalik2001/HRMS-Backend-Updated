package com.ahom.hrms.dto;

public class EmergencyContactInfoDto {
	
	private String emergencyContactName;
	private String emergencyContactMobile;
	private String emergencyContactEmail;
	private String emergencyContactAddress;
	
	public EmergencyContactInfoDto() {
		
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

	public EmergencyContactInfoDto(String emergencyContactName, String emergencyContactMobile,
			String emergencyContactEmail, String emergencyContactAddress) {
		super();
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactMobile = emergencyContactMobile;
		this.emergencyContactEmail = emergencyContactEmail;
		this.emergencyContactAddress = emergencyContactAddress;
	}

}
