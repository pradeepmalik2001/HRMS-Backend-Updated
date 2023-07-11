package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.AdvanceSalaryRepository;
import com.ahom.hrms.Repository.EmployeeRepository;
import com.ahom.hrms.dto.AdvanceSalaryDto;
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
    public AdvanceSalary updatePerMonthDeduction(AdvanceSalary advanceSalary,String id)
    {
        AdvanceSalary advanceSalary1=advanceSalaryRepository.findByEmployeeId(id);
        if(advanceSalary1!=null)
        {
            advanceSalary1.setAmountToPayPerMonth(advanceSalary.getAmountToPayPerMonth());
            advanceSalaryRepository.save(advanceSalary1);
        }
        return advanceSalary1;
    }

    @Override
    public AdvanceSalary saveSalary(AdvanceSalary advanceSalary)
    {
        Employee employee=employeeRepository.findById(advanceSalary.getEmployeeId()).orElse(null);
        if(employee!=null)
        {
            LocalDate localDate=LocalDate.now();
            advanceSalary.setDate(localDate);
            advanceSalary.setRemainingAdvance(advanceSalary.getAdvance());
            advanceSalary.setAmountToPayPerMonth(advanceSalary.getAdvance()/ advanceSalary.getAmountToPayWithinMonth());
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
        return advanceSalaryRepository.findAll();
    }

    @Override
    public AdvanceSalary updateRemainingAdavnceMonth(AdvanceSalary advanceSalary)
    {
        return advanceSalaryRepository.save(advanceSalary);

    }

    @Override
    public AdvanceSalary getById(String employeeId) {
        AdvanceSalary advanceSalary=advanceSalaryRepository.findByEmployeeId(employeeId);
        if (advanceSalary!=null){
            return advanceSalary;
        }else
            throw new RuntimeException();
    }

    @Override
    public AdvanceSalary updateSalary(AdvanceSalary advanceSalary, String id)
    {
        AdvanceSalary advanceSalary1=advanceSalaryRepository.findByEmployeeId(id);
        if(advanceSalary1.getRemainingAdvance()<advanceSalary1.getAmountToPayPerMonth())
        {
            advanceSalary1.setAmountToPayPerMonth(advanceSalary1.getRemainingAdvance());
            advanceSalary1.setRemainingAdvance(advanceSalary1.getRemainingAdvance()-advanceSalary.getAmountToPayPerMonth());
            advanceSalaryRepository.save(advanceSalary1);
        }
        else {
            advanceSalary1.setRemainingAdvance(advanceSalary.getRemainingAdvance() - advanceSalary.getAmountToPayPerMonth());
            advanceSalaryRepository.save(advanceSalary1);
        }

        return advanceSalary1;
    }

    @Override
    public AdvanceSalary findByEmployeeId(String employeeId)
    {
        return advanceSalaryRepository.findByEmployeeId(employeeId);
    }

}
