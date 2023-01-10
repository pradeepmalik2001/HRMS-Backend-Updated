package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalarySetup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String financialYear;

//    private int employeeId;
    private String month;
    private String annualSalary;

    @ManyToOne
    @JoinColumn(name = "emp_id",referencedColumnName = "employeeId")
    private BasicEmployee basicEmployee;

//    @ManyToOne
//    private PayRoll payRoll;


}
