package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name="Add_holiday")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddHoliday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	@NotEmpty(message="Holiday Name is Mandatory")
	private String holidayName;
	@NotEmpty(message="Holiday Type is Mandatory")
	private String holidayType;
	@NotEmpty(message = "From Date is Mandatory")
	private String fromDate;
	@NotEmpty(message="To Date is Mandatory")
	private String toDate;

	private LocalDate date;

}
