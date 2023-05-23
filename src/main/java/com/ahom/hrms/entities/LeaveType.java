package com.ahom.hrms.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class LeaveType
{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private int id;

@NotEmpty(message="LeaveType is Mandatory ")
private String leaveType;

@NotEmpty(message="Description is Mandatory")
private String description;

}
