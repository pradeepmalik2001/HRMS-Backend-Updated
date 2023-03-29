package com.ahom.hrms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalarySetup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Transient
//    private String name;
    private String financialYear;

//    @Transient
//    private int employeeId;
    private String month;
    private int annualSalary;

    //@OneToMany(mappedBy = "employeeId",cascade = CascadeType.ALL)
//     @JoinColumn(name = "employeeId",referencedColumnName = "employeeId")
//    private List<BasicEmployee> basicEmployee=new ArrayList<>();
    @OneToMany(mappedBy = "employeeId", fetch = FetchType.LAZY)
    //@JoinColumn(name = "salary_setup_id")
    private Set<BasicEmployee> basicEmployee = new HashSet<>();












//    @ManyToOne
//    private PayRoll payRoll;


}
