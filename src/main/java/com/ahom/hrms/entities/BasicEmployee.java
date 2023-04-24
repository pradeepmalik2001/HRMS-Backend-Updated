package com.ahom.hrms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

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
	private String employeeName;
	private String whichCompany;
	private String selectDepartment;
	private String designation;
	private String email;
	private String mobile;
	private String joiningDate;
	private String reportingTo;
	private String dob;
	private String workType;
	private int ctc;
	private String pfnumber;
	private String panNumber;
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
//@ManyToOne
//private SalarySetup salarySetup;
//	@OneToOne(fetch = FetchType.LAZY)
//	@Fetch(FetchMode.JOIN)
//	private BankingInfo bankingInfo;
	


}
