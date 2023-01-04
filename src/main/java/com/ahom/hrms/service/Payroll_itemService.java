package com.ahom.hrms.service;

import com.ahom.hrms.dto.Payroll_itemDto;

import java.util.List;

public interface Payroll_itemService {
    Payroll_itemDto updatePayrollitem(Payroll_itemDto payroll_Itemdto);
    void savePayrollitem(Payroll_itemDto payroll_Itemdto);
    List<Payroll_itemDto> getAllpayrollitem();
    void deletePayrollitem(int empId);
}
