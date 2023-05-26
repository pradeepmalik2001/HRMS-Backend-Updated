package com.ahom.hrms.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Type;
import java.util.Date;


@Entity
@Data
@Table(name="overtime")
public class OverTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@NotEmpty(message = "Employee Name can`t be Empty")
	private String selectEmployee;

	@Temporal(TemporalType.DATE)
	private Date date;

	@NotEmpty(message = "Start Time is Mandatory")
	private String startTime;

	@NotEmpty(message = "End Time is Mandatory")
	private String endTime;

	@NotEmpty(message = "Write Something Here")
	private String description;
	
}
