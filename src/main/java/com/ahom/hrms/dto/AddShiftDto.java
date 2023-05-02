package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddShiftDto {
	private int id;
	private String country;
	private String employee;
	private String date;
	private String startTime;
	private String endTime;
}
