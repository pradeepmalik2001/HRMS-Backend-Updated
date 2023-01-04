package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class PayheadMaster {
@Id
@GeneratedValue(strategy = GenerationType.AUTO )
private int id;
@NotBlank(message="not null payhead is mandatory")
private String payhead;
@NotBlank(message="not null type is mandatory")
private String type;
@NotBlank(message="not null shortCode is mandatory")
private String shortCode;
@NotBlank(message="not null calculatedType is mandatory")
private String calculatedType;
@NotBlank(message="not null value is mandatory")
private float value;
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
 * @return the payhead
 */
public String getPayhead() {
	return payhead;
}
/**
 * @param payhead the payhead to set
 */
public void setPayhead(String payhead) {
	this.payhead = payhead;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the shortCode
 */
public String getShortCode() {
	return shortCode;
}
/**
 * @param shortCode the shortCode to set
 */
public void setShortCode(String shortCode) {
	this.shortCode = shortCode;
}
/**
 * @return the calculatedType
 */
public String getCalculatedType() {
	return calculatedType;
}
/**
 * @param calculatedType the calculatedType to set
 */
public void setCalculatedType(String calculatedType) {
	this.calculatedType = calculatedType;
}
/**
 * @return the value
 */
public float getValue() {
	return value;
}
/**
 * @param value the value to set
 */
public void setValue(float value) {
	this.value = value;
}
}
