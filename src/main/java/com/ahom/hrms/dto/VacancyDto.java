package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {

	
	private String hiringManager;
	private String jobTitle;
	private String vacancyName;
	private int numberOfPosition;
	private String jobLocation;
	private String jobDescription;
	private boolean active;
	
}
