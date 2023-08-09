package com.ahom.hrms.entities;


import com.ahom.hrms.constant.PrefixAndSequence;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CreateLeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "leave_seq")
	@GenericGenerator(name = "leave_seq",
			strategy = "com.ahom.hrms.constant.PrefixAndSequence",
			parameters = {
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "LId_"),
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
			})

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

	private LocalDate date;

	@OneToOne(targetEntity = LeaveRecord.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonBackReference
	LeaveRecord leaveRecord;

}
