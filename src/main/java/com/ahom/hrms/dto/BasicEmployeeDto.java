package com.ahom.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicEmployeeDto {


	private int employeeId;
	@NotBlank(message = "Employee name can not be null")
	private String employeeName;

	@NotBlank(message = "company required")
	private String whichCompany;

	@NotBlank(message = "Department Required")
	private String selectDepartment;

	@NotBlank(message = "Designation Required")
	private String designation;

	@Email
	private String email;

	@NotBlank(message = "Mobile Number Required")
	private String mobile;

	@NotBlank(message = "Joining Date Required")
	@DateTimeFormat(fallbackPatterns = "yyyy-mm-dd")
	private String joiningDate;

	@NotBlank(message = "Reporting To Required")
	private String reportingTo;

	@NotBlank(message = "DOB Required")
	private String dob;

	@NotBlank(message = "Work Type Required")
	private String workType;

	@NotNull(message = "CTC Required")
	private int ctc;

	private String pfnumber;

	@Column(nullable = false,unique = true)
	@NotBlank(message = "PAN Number Required")
//	@Min(10)
//	@Max(10)
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "PAN number should be in proper format like ABCDE1234A ")
	private String panNumber;

	@NotBlank(message = "Aadhaar Number Required")
//	@Min(12)
//	@Max(12)
	@Column(nullable = false,unique = true)

	@Pattern(regexp = "[0-9]{12}",message = "Aadhaar Number should be in format ")
	private String aadhaarNumber;
	
	

	
}
