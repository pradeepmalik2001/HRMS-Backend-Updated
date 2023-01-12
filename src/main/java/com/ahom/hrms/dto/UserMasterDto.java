package com.ahom.hrms.dto;

import java.util.List;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;


public class UserMasterDto {
	
	private int id;
	//@NotBlank(message = "select anyone")
	private String departmentName;
	//@NotNull(message="employee name shouldn't be null")
	private String employeeName;
	//@NotNull(message="employee name shouldn't be null")
	private String employeeCode;
	//@NotBlank(message = "it is mandatory")
	private String userName;
	private String password;
	
	private String roleName;
	private List<RoleDto> roles;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

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
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	
}
