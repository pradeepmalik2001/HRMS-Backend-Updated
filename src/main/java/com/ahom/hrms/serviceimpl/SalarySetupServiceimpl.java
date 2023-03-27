package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.Repository.EmployeeAllowancesRepository;
import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.dto.EmpAllowance;
import com.ahom.hrms.dto.SalarySetupDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.EmployeeAllowances;
import com.ahom.hrms.entities.SalarySetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalarySetupServiceimpl implements com.ahom.hrms.service.SalarySetupService {

    @Autowired
    SalarySetupRepository salarySetupRepository;
    @Autowired
    BasicEmployeeRepository basicEmployeeRepository;
    @Autowired
    EmployeeAllowancesRepository employeeAllowancesRepository;
    @Override
    public SalarySetup saveDeduction(SalarySetup salarySetupDto)
    {
        BasicEmployee basicEmployee=basicEmployeeRepository.findById(salarySetupDto.getId()).orElse(null);
        if (basicEmployee==null)
        {
            throw new RuntimeException("employeeId not exist");
        }else
        {
            salarySetupDto.setBasicEmployee((Set<BasicEmployee>) basicEmployee);
        salarySetupRepository.save(salarySetupDto);
    }
    return salarySetupDto;
    }
    @Override
    public void deleteSalary(int id ) {

        salarySetupRepository.deleteById(id);
    }
    @Override
    public List<SalarySetupDto> getAll(){
        List<SalarySetup> salarySetups= this.salarySetupRepository.findAll();
        List<SalarySetupDto> Dto  = salarySetups.stream().map(deduct ->this.SalarySetupToSalarySetupDto(deduct)).collect(Collectors.toList());

        return Dto;
    }
    public SalarySetup salarySetupDtoTosalarySetup(SalarySetupDto salarySetupDto)
    {
        SalarySetup salarySetup=new SalarySetup();

        salarySetup.setId(salarySetupDto.getId());
        salarySetup.setFinancialYear(salarySetupDto.getFinancialYear());
        salarySetup.setMonth(salarySetupDto.getMonth());
        salarySetup.setAnnualSalary(salarySetupDto.getAnnualSalary());
//        salarySetup.setEmployeeId(salarySetupDto.getEmployeeId());

        return salarySetup;
    }

    public SalarySetupDto SalarySetupToSalarySetupDto(SalarySetup salarySetup)

    {
        SalarySetupDto salarySetupDto=new SalarySetupDto();
        salarySetupDto.setId(salarySetup.getId());
        salarySetupDto.setAnnualSalary(salarySetup.getAnnualSalary());
        salarySetupDto.setFinancialYear(salarySetup.getFinancialYear());
        salarySetupDto.setMonth(salarySetup.getMonth());
        return salarySetupDto;
    }

    public SalarySetupDto updateSalarySetup(SalarySetupDto salarySetupDto) {
        salarySetupRepository.save(salarySetupDtoTosalarySetup(salarySetupDto));
        return salarySetupDto;

    }

    public double salaryCalculation(int employeeId)
    {
        SalarySetup id1 = salarySetupRepository.findByEmployeeId(employeeId);
        double annualSalary = id1.getAnnualSalary();
//        double PF=(annualSalary / 100) * 12;
//        double total=annualSalary-PF;
        double monthly=annualSalary/12;
        return monthly;
    }

    public double pfCalculation(int employeeId)
    {
        SalarySetup id1 = salarySetupRepository.findByEmployeeId(employeeId);
        double annualSalary = id1.getAnnualSalary();
        double month=annualSalary/12;
        double PF=(month / 100) * 12;
        return PF;
    }
    public double hraCalculation(int employeeId)
    {
        SalarySetup id1 = salarySetupRepository.findByEmployeeId(employeeId);
        double annualSalary = id1.getAnnualSalary();
        double month=annualSalary/12;
        double hra=(double) (month / 100) * 15;
        return hra;
    }
    public double grossEarning(int employeeId)
    {
//        SalarySetup id1 = salarySetupRepository.findByEmployeeId(employeeId);
//        double annualSalary = id1.getAnnualSalary();
//        double month=annualSalary/12;
//        double hra=(double) (month / 100) * 15;
        EmpAllowance salary=salarySetupRepository.findByAllowanceId(employeeId);

        System.out.println(salary.toString());
//        EmployeeAllowances e=new EmployeeAllowances();
//        double ammount = e.getAmmount();
//        SalarySetup spca = SpecialAllowance;
//        double Conveyance=(month / 100) * 12;
//        return hra+month+SpecialAllowance+Conveyance;
        return salary.getSalary();
    }


}
