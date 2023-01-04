package com.ahom.hrms.dto;

import com.ahom.hrms.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayRollDto {
	private int id;
	private String refNo;
	private String dateFrom;
	private String dateTo;
	private String type;
	private String status;
	private String dateCreated;

	private List<Allowances> allowances;
	private List<Deduction> deduction = new ArrayList<>();
	private List<EmployeeDeduction> deductions = new ArrayList<>();
	private List<Payroll_Item> payroll_items;
	private  List<SalarySetup>  salarySetups;
}
