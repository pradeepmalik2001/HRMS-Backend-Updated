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

    private String gender;

    private String bloodGroup;
   
    @NotEmpty(message = "Fill the address")
    private String address;


    private String description;


    @OneToOne(targetEntity = BasicEmployee.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BasicEmployee basicEmployee;


}
