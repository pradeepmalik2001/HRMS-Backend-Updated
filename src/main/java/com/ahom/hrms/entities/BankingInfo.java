package com.ahom.hrms.entities;

import javax.persistence.*;

import com.lowagie.text.pdf.PdfPCell;

@Entity
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
	@OneToOne
	@JoinColumn(name = "basic_employee_employee_id")
	private BasicEmployee basicEmployee;
//
//	@OneToOne
//	private WorkInformation workInformation;

//	public BasicEmployee getBasicEmployee() {
//		return basicEmployee;
//	}
//
//	public void setBasicEmployee(BasicEmployee basicEmployee2) {
//		this.basicEmployee = basicEmployee2;
//	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public BankingInfo(int id, String bankAccountNo, String bankName, String bankBranch,
					   String ifscCode, String name) {
		this.id = id;
		this.bankAccountNo = bankAccountNo;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.ifscCode = ifscCode;
		this.name = name;
	}

	public BankingInfo() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BasicEmployee getBasicEmployee() {
		return basicEmployee;
	}

	public void setBasicEmployee(BasicEmployee basicEmployee) {
		this.basicEmployee = basicEmployee;
	}
}
