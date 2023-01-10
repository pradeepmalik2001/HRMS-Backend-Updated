package com.ahom.hrms.entities;

import javax.persistence.*;

@Entity
public class AttendanceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fromDate;
	private String toDate;
	//	private String selectEmployee;
	@Transient
	private int empId;
	@OneToOne
	@JoinColumn(name = "emp_id", referencedColumnName = "employeeId")
	private BasicEmployee basicEmployee;

	public String getFromDate() {
		return fromDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public BasicEmployee getBasicEmployee() {
		return basicEmployee;
	}

	public void setBasicEmployee(BasicEmployee basicEmployee) {
		this.basicEmployee = basicEmployee;
	}

	public AttendanceDetails(int id, String fromDate, String toDate, int empId, BasicEmployee basicEmployee) {
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.empId = empId;
		this.basicEmployee = basicEmployee;
	}

	public AttendanceDetails() {
	}
}