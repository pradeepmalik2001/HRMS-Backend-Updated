package com.ahom.hrms.service;

import com.ahom.hrms.dto.PayRollDto;

import java.util.List;

public interface PayrollService {
    void savePayroll(PayRollDto payrollDto);

    List<PayRollDto> getAllPayroll();

    void deletePayroll(int Id);

    PayRollDto payrollById(Integer Id);
//    PayRollDto findOneByIdAndrefNoAndtype(int Id);
}