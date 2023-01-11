package com.ahom.hrms.service;

import com.ahom.hrms.dto.SalarySetupDto;
import com.ahom.hrms.entities.SalarySetup;

import java.util.List;

public interface SalarySetupService {
    SalarySetup saveDeduction(SalarySetup salarySetupDto);
    void deleteSalary(int id );
    List<SalarySetupDto> getAll();
}
