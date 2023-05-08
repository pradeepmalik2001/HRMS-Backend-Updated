package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLeaveRequestDto {
	private int id;

	private String selectEmployee;
	private String leaveApprover;
	private String leaveType;

	private String startDate;
	private String endDate;
//	private String days;
	private String reasonForLeave;
	private boolean approve;

	private BasicEmployee basicEmployee;

}
