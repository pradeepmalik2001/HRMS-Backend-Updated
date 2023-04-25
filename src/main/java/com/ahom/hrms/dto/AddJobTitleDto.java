package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddJobTitleDto {
	private int id;

	@NotEmpty(message = "Please Fill Job Title")
	private String jobTitles;

	

}
