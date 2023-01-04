package com.ahom.hrms.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CreateLeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Double availableBalance;
	private String selectEmployee;
	private String leaveApprover;
	private String leaveType;
	private String leaveFor;
	private String startDate;
	private String endDate;
	private String days;
	private String reasonForLeave;
}
