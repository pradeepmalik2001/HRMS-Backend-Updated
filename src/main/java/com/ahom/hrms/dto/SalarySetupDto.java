package com.ahom.hrms.dto;

import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.PayRoll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalarySetupDto {
    private int id;
    private String financialYear;
//    private int employeeId;
    private String month;
    private String annualSalary;

    private BasicEmployee basicEmployee;


}
