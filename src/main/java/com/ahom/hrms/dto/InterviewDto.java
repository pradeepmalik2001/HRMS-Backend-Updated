package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InterviewDto {

	private int id;
	private String vacancy;
	private String candidate;
	private String time;
	private String date;
	private String method;
	private String status;
	private String comment;

}
