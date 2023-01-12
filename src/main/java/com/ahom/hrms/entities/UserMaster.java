package com.ahom.hrms.entities;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.parameters.P;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_master")
public class  UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "select anyone")
	private String departmentName;
	@NotNull(message="employee name shouldn't be null")
	private String employeeName;
	@NotNull(message="employee name shouldn't be null")
	private String employeeCode;
	@NotBlank(message = "it is mandatory")
	private String userName;
	private String password;
//	@NotBlank(message = "please select role type")
//	private String roleType;

//	@OneToOne
//	private PayRoll payRoll;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getRoleType() {
//		return roleType;
//	}
//	public void setRoleType(String roleType) {
//		this.roleType = roleType;
//	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = 
	@JoinColumn(name ="user_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))

	private List<Role> roles;
//	@Transient
	private String roleName;

//	public PayRoll getPayRoll() {
//		return payRoll;
//	}
//
//	public void setPayRoll(PayRoll payRoll) {
//		this.payRoll = payRoll;
//	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
