package com.ahom.hrms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lowagie.text.pdf.PdfPCell;
import lombok.Data;

@Entity
@Data
@Table(name = "banking_info")
public class BankingInfo {

	@Id
	private String id;

	@NotEmpty(message = "Please Enter Account Number")
	@Column(unique = true)
	private String bankAccountNo;

	@NotEmpty(message = "Please Enter Bank Name")
	@Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,30}",message = "only alphabets")
	private String bankName;

	@NotEmpty(message = "Please Enter Bank Branch")
	private String bankBranch;

	@NotEmpty(message = "Please Enter IFSC Code")
	@Pattern(regexp = "[A-Z]{4}[A-Z0-9]{7}",message = "IFSC Code should be in format HDFC090909")
	private String ifscCode;

	@NotEmpty(message = "Please Enter Your Name")
	private String name;

	@OneToOne(targetEntity = BasicEmployee.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "basic_employee_employee_id")


//	@JsonBackReference
	private BasicEmployee basicEmployee1;



	private double basicSalary;
	private double grossSalary;

//	public void setBasicSalary(double basicSalary) {
//		this.basicSalary = basicSalary;
//	}
//
//	public void setGrossSalary(double grossSalary) {
//		this.grossSalary = grossSalary;
//	}
}


