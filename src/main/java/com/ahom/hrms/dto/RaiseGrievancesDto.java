package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaiseGrievancesDto {
	private String grievanceType;
	private String title;
	private String uploadAttechment;
	private String description;
	


}
