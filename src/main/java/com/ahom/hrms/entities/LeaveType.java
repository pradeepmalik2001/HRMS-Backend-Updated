package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class LeaveType {
@Id
@GeneratedValue(strategy = GenerationType.AUTO )
private int id;

@NotBlank(message="not null leaveType is mandatory ")
private String leaveType;
@NotBlank(message="not null description is mandatory")
private String description;
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the leaveType
 */
public String getLeaveType() {
	return leaveType;
}
/**
 * @param leaveType the leaveType to set
 */
public void setLeaveType(String leaveType) {
	this.leaveType = leaveType;
}
/**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}

}
