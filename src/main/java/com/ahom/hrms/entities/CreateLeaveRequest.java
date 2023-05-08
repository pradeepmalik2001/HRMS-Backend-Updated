package com.ahom.hrms.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CreateLeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String selectEmployee;
	private String leaveApprover;
	private String leaveType;
	private String startDate;
	private String endDate;
//	private String days;
	private String reasonForLeave;
	private boolean approve;

//	@OneToOne(targetEntity = BasicEmployee.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	private BasicEmployee basicEmployee;
}
