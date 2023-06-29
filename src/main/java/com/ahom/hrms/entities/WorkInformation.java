package com.ahom.hrms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "work_info")
public class WorkInformation {

    @Id
    @NotBlank
    private String workId;
    @NotEmpty(message = "Employee name can not be null")
    private String employeeName;
    @NotEmpty(message = "Employment type can not be null")
    private String employmentType;
    @NotEmpty(message = "Branch required")
    private String officeBranch;
    @NotEmpty(message = "specify gender")
    private String gender;
    @NotEmpty(message = "Blood Group needed")
    private String bloodGroup;
    @NotEmpty(message = "Specify Employment type")
    private String employeeType;
    @NotEmpty(message = "Fill the address")
    private String address;
    @NotEmpty(message = "Description is empty")
    private String description;


    @OneToOne(targetEntity = BasicEmployee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BasicEmployee basicEmployee;


}
