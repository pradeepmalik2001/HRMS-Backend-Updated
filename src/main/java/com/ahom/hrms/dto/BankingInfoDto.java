package com.ahom.hrms.dto;

public class BankingInfoDto {
	
	private String bankAccountNo;
	private String bankName;
	private String bankBranch;
	private String ifscCode;
//	private String paymentType;
	private String name;
	private int basicId;

	public int getBasicId() {
		return basicId;
	}

	public void setBasicId(int basicId) {
		this.basicId = basicId;
	}

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

	public BankingInfoDto() {
		
	}

	public BankingInfoDto(String bankAccountNo, String bankName, String bankBranch,
						  String ifscCode, String name) {
		this.bankAccountNo = bankAccountNo;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.ifscCode = ifscCode;
		this.name = name;
	}
}
