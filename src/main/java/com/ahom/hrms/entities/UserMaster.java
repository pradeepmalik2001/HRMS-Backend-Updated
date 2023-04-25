package com.ahom.hrms.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.core.parameters.P;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_master")
public class  UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "dkshd")

	private String departmentName;
//	@NotNull(message="employee name shouldn't be null")
	@Column(nullable = false)
	private String employeeName;
	@NotNull(message="employee name shouldn't be null")

	@NotBlank(message = "it is mandatory")
	@Column(unique = true)
	private String userName;
//	@JsonProperty(access = JsonProperty.Access.AUTO)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{5,}$")
	private String password;
//	private  String aadhaarNumber;
//	private String panNumber;

//	public String getAadhaarNumber() {
//		return aadhaarNumber;
//	}
//
//	public void setAadhaarNumber(String aadhaarNumber) {
//		this.aadhaarNumber = aadhaarNumber;
//	}
//
//	public String getPanNumber() {
//		return panNumber;
//	}
//
//	public void setPanNumber(String panNumber) {
//		this.panNumber = panNumber;
//	}

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

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@ManyToMany(targetEntity = Role.class, fetch=FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = 
	@JoinColumn(name ="user_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))

	private List<Role> roles;
//	@Transient
	private String roleName;



	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
