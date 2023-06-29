package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddShiftDto {
	private String id;

	@NotEmpty(message = "Country Name Can`t be Empty")
	private String country;

	@NotEmpty(message = "Employee Name Can`t be Empty")
	private String employee;

	private String date;

	@NotEmpty(message = "Start Time Can`t be Empty")
	private String startTime;

	@NotEmpty(message = "End Time Can`t be Empty")
	private String endTime;

	@NotEmpty(message = "Username Can`t be Empty")
	private String userName;
}
