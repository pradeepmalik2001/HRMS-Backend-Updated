package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Add_holiday")
public class AddHoliday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	@NotEmpty(message="Holiday Name is Mandatory")
	private String holidayName;
	@NotEmpty(message="Holiday Type is Mandatory")
	private String holidayType;
	@NotEmpty(message = "From Date is Mandatory")
	private String fromDate;
	@NotEmpty(message="To Date is Mandatory")
	private String toDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
