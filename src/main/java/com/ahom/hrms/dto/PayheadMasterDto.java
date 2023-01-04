package com.ahom.hrms.dto;


public class PayheadMasterDto {	
	private int id;
	private String payhead;
	private String type;
	private String shortCode;
	private String calculatedType;
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
	 * @return the payheaD
	 */
	public String getPayhead() {
		return payhead;
	}
	/**
	 * @param payheaD the payheaD to set
	 */
	public void setPayhead(String payheaD) {
		this.payhead = payheaD;
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
