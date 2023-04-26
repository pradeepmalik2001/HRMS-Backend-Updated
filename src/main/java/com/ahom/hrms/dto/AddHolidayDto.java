package com.ahom.hrms.dto;

import javax.validation.constraints.NotEmpty;

public class AddHolidayDto {
	private int id;
	@NotEmpty(message="Holiday Name is Mandatory")
	private String holidayName;
	@NotEmpty(message="Holiday Type is Mandatory")
	private String holidayType;
	@NotEmpty(message = "From Date is Mandatory")
	private String fromDate;
	@NotEmpty(message="To Date is Mandatory")
	private String toDate;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the holidayName
	 */
	public String getHolidayName() {
		return holidayName;
	}
	/**
	 * @param holidayName the holidayName to set
	 */
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	/**
	 * @return the holidayType
	 */
	public String getHolidayType() {
		return holidayType;
	}
	/**
	 * @param holidayType the holidayType to set
	 */
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
