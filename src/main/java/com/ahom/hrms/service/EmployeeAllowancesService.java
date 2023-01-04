package com.ahom.hrms.service;

import com.ahom.hrms.dto.EmployeeAllowancesDto;

import java.util.List;

public interface EmployeeAllowancesService {
    void saveEmployeeAllowances(EmployeeAllowancesDto employeeAllowancesDto);
    List<EmployeeAllowancesDto> getAllEmployeeAllowances ();
    void deleteEmployeeAllowance(int Id);
    EmployeeAllowancesDto employeeAllowancesById(Integer Id);

}

