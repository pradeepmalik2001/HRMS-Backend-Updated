package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.LeaveRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLeaveRequestDto {
	private String id;
	private String employeeId;
	@NotEmpty(message = "Employee Name can`t be Empty")
	private String selectEmployee;

	@NotEmpty(message = "Approver Name can`t be Empty")
	private String leaveApprover;

	@NotEmpty(message = "Leave Type can`t be Empty")
	private String leaveType;

	@NotEmpty(message = "Start Date is Compulsory")
	private String startDate;

	@NotEmpty(message = "Last Date is Compulsory")
	private String endDate;

	@NotEmpty(message = "Reason For Leave is Mandatory")
	private String reasonForLeave;


	private String status;

	private String email;

	private double noOfDays;

	LeaveRecord leaveRecord;

}
