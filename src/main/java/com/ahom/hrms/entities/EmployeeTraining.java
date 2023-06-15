package com.ahom.hrms.entities;
import com.ahom.hrms.constant.PrefixAndSequence;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class EmployeeTraining
{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_seq")
	@GenericGenerator(name = "branch_seq",
			strategy = "com.ahom.hrms.constant.PrefixAndSequence",
			parameters = {
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "ET_"),
					@org.hibernate.annotations.Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
			})
	private String id;

	@NotEmpty(message = "Event Name can`t be Empty")
	private String eventName;

	@NotEmpty(message = "Training name can`t be Empty")
	private String trainingName;

	@NotEmpty(message = "Employee Name can`t be Empty")
	private String employee;

}
