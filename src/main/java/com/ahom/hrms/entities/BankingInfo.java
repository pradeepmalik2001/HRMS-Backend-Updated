package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
@Table(name = "banking_info")
public class BankingInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bankAccountNo;
	private String bankName;
	private String bankBranch;
	private String ifscCode;
	private String paymentType;
	private String pan;
	
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
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}

	public BankingInfo(int id, String bankAccountNo, String bankName, String bankBranch, String ifscCode, String paymentType, String pan) {
		this.id = id;
		this.bankAccountNo = bankAccountNo;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.ifscCode = ifscCode;
		this.paymentType = paymentType;
		this.pan = pan;
	}

	public BankingInfo() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
