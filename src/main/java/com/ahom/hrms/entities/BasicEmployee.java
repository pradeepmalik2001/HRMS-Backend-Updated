package com.ahom.hrms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "basic_employee")
public class BasicEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

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
	private String joiningDate;

	@NotBlank(message = "Reporting To Required")
	private String reportingTo;

	private LocalDate dob;

	@NotBlank(message = "Work Type Required")
	private String workType;

	@NotNull(message = "CTC Required")
	private int ctc;

	private String pfnumber;

	@Column(nullable = false,unique = true)
	@NotBlank(message = "PAN Number Required")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "PAN number should be in proper format like ABCDE1234A ")
	private String panNumber;

	@NotBlank(message = "Aadhaar Number Required")
	@Column(nullable = false,unique = true)
	@Pattern(regexp = "[0-9]{12}",message = "Aadhaar Number should be in format ")
	private String aadhaarNumber;

	@OneToOne(mappedBy = "basicEmployee1", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
			@JsonBackReference
	BankingInfo bankingInfo;

	@OneToOne(mappedBy = "basicEmployee",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
			@JsonBackReference
	WorkInformation workInformation;

	@OneToOne(mappedBy = "basicEmployee",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonBackReference
	EmergencyContactInfo emergencyContactInfo;



}
