package com.ahom.hrms.service;

import com.ahom.hrms.dto.SalarySetupDto;

import java.util.List;

public interface SalarySetupService {
    void saveDeduction(SalarySetupDto salarySetupDto);
    void deleteSalary(int id );
    List<SalarySetupDto> getAll();
}
