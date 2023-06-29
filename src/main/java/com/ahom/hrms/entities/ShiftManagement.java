package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shift_management")
public class ShiftManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_seq")
	@GenericGenerator(name = "branch_seq",
			strategy = "com.ahom.hrms.constant.PrefixAndSequence",
			parameters = {
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "SHIFT_"),
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
			})
	private String id;

	@NotEmpty(message = "Country Name Can`t be Empty")
	private String country;

	@NotEmpty(message = "Employee Name Can`t be Empty")
	private String employee;

	private String date;

	@NotEmpty(message = "Start Time Can`t be Empty")
	private String startTime;

	@NotEmpty(message = "End Time Can`t be Empty")
	private String endTime;

	@NotEmpty(message = "Username Can`t be Empty")
	private String userName;
}