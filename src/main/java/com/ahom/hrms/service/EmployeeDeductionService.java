package com.ahom.hrms.service;

import com.ahom.hrms.dto.EmployeeDeductionDto;

import java.util.List;

public interface EmployeeDeductionService {
    void saveEmp(EmployeeDeductionDto empDeductiondto);
    List<EmployeeDeductionDto> getAllempdeduction ();
    void deleteempdeduction(int Id);
}
