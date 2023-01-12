package com.ahom.hrms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;
	@Column(unique=true)
	private String roleName;

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@JsonBackReference
	
	public List<UserMaster> getUserMasters() {
		return userMasters;
	}
	public void setUserMasters(List<UserMaster> userMasters) {
		this.userMasters = userMasters;
	}
	

	public Role(int roleId, String roleName, List<UserMaster> userMasters) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.userMasters = userMasters;
	}
	public Role() {

	}
	@ManyToMany(targetEntity = UserMaster.class, mappedBy = "roles",
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<UserMaster> userMasters;


}
