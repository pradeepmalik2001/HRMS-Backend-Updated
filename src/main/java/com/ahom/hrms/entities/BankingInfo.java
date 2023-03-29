package com.ahom.hrms.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lowagie.text.pdf.PdfPCell;
import lombok.Data;

@Entity
@Data
@Table(name = "banking_info")
public class BankingInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bankAccountNo;
	private String bankName;
	private String bankBranch;
	private String ifscCode;
	//	private String paymentType;
	private String name;
	@OneToOne(targetEntity = BasicEmployee.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "basic_employee_employee_id")

	@JsonBackReference
	private BasicEmployee basicEmployee1;
}


