package com.ahom.hrms.service;

import com.ahom.hrms.entities.AdvanceSalary;

import java.util.List;

public interface AdvanceSalaryService
{
    public AdvanceSalary saveSalary(AdvanceSalary advanceSalary);

    public List<AdvanceSalary> getAllSalary();

    public AdvanceSalary updateSalary(AdvanceSalary advanceSalary,String id);

    public AdvanceSalary findById(String id);

    public AdvanceSalary updatePerMonthDeduction(AdvanceSalary advanceSalary,String id);

    AdvanceSalary updateRemainingAdavnceMonth(AdvanceSalary advanceSalary);
}