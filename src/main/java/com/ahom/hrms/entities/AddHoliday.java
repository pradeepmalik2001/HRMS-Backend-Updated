package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Add_holiday")
public class AddHoliday {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	@NotBlank(message="not null holidayName is mandatory")
	private String holidayName;
	@NotBlank(message="not null holidayType is mandatory")
	private String holidayType;
	@NotBlank(message = "from date is mandatory")
	private String fromDate;
	@NotBlank(message="to date is mandatory")
	private String toDate;
	/**
	 * @return the id
	 */
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
