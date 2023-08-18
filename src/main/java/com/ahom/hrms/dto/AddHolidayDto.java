package com.ahom.hrms.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Getter
@Setter
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
	private LocalDate date;

}
