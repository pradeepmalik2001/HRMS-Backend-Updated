package com.ahom.hrms.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_master")
public class  UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Department name is mandatory")
	private String departmentName;

	@Column(nullable = false)
	@NotBlank(message = "Name can not be empty")
//	@Pattern(regexp = "^[a-zA-Z][a-zA-Z\\\\s]+$")
	private String employeeName;


	@NotBlank(message = "user name is mandatory")
	@Column(unique = true)
	@Email
	private String userName;


	@JsonProperty()
	@Pattern(regexp = "^.*(?=.{5,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
			message = "must contain one upperCase and special character")
	private String password;

	@Transient
	private String confirmPassword;

	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns =
			@JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))

	private List<Role> roles;

	private String roleName;

}
