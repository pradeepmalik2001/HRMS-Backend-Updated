package com.ahom.hrms.service;

import com.ahom.hrms.dto.PayRollDto;
import com.ahom.hrms.entities.PayRoll;

import java.util.List;

public interface PayrollService {
    void savePayroll(PayRollDto payrollDto);

    List<PayRollDto> getAllPayroll();

    void deletePayroll(int Id);

    PayRollDto savePayroll(PayRoll payroll);

    PayRollDto payrollById(Integer Id);

    PayRoll payrollDtoToPayroll(PayRollDto payrollDto);

    PayRollDto payrollToPayrollDto(PayRoll payroll);
//    PayRollDto findOneByIdAndrefNoAndtype(int Id);


}