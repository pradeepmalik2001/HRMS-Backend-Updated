package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.AdvanceSalaryRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.entities.AdvanceSalary;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.exception.CustomException;
import com.ahom.hrms.service.AdvanceSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdvanceSalaryServiceImpl implements AdvanceSalaryService
{
    @Autowired
    AdvanceSalaryRepository advanceSalaryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public AdvanceSalary saveSalary(AdvanceSalary advanceSalary)
    {
        Employee employee=employeeRepository.findById(advanceSalary.getId()).orElse(null);
        if(employee!=null)
        {
            LocalDate localDate=LocalDate.now();
            advanceSalary.setDate(localDate);
            advanceSalaryRepository.save(advanceSalary);
        }
        else {
            throw new CustomException("Employee Not Found for ID : "+advanceSalary.getId());
        }
        return advanceSalary;
    }

    @Override
    public List<AdvanceSalary> getAllSalary()
    {
        List<AdvanceSalary> advanceSalaries=advanceSalaryRepository.findAll();
        return advanceSalaries;
    }

    @Override
    public AdvanceSalary updateSalary(AdvanceSalary advanceSalary, int id)
    {
        return null;
    }
}
