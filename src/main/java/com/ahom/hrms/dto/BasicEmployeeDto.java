package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor

@Data
public class BasicEmployeeDto {


	@NotEmpty(message = "Employee Id Required")
	private String employeeId;
	@NotEmpty(message = "Employee name can not be null")
	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,30}")
	private String employeeName;

	@NotBlank(message = "company required")
	private String whichCompany;

	@NotBlank(message = "Department Required")
	private String selectDepartment;

	@NotBlank(message = "Designation Required")
	private String designation;

	@Email
	@Column(nullable = false,unique = true)
	@NotBlank(message = "Email needs to entered")
	private String email;

	@NotBlank(message = "Mobile Number Required")
	@Column(nullable = false,unique = true)
	@Size(min = 10,max = 10,message = "Number should be of 10 digits")
	private String mobile;

	@NotBlank(message = "Joining Date Required")
	@DateTimeFormat(fallbackPatterns = "yyyy-mm-dd")
	private String joiningDate;

	@NotBlank(message = "Reporting To Required")
	private String reportingTo;

//	@NotBlank(message = "DOB Required")
	@NotNull
	private LocalDate dob;

	@NotBlank(message = "Work Type Required")
	private String workType;

	@NotNull
	private Long ctc;

	private String pfnumber;

	@Column(nullable = false,unique = true)
	@NotBlank(message = "PAN Number Required")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "PAN number should be in proper format like ABCDE1234A ")
	private String panNumber;

	@NotBlank(message = "Aadhaar Number Required")
	@Column(nullable = false,unique = true)
	@Pattern(regexp = "[0-9]{12}",message = "Aadhaar Number should be in format ")
	private String aadhaarNumber;
	
	

	
}
