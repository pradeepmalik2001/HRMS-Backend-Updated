package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDetailsDto {
	

	private String fromDate;
	private String toDate;
	private String selectEmployee;
	private int empId;
	private BasicEmployee basicEmployee;

}
