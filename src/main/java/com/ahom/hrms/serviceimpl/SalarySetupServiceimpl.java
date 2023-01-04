package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.SalarySetupRepository;
import com.ahom.hrms.dto.SalarySetupDto;
import com.ahom.hrms.entities.SalarySetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalarySetupServiceimpl implements com.ahom.hrms.service.SalarySetupService {

    @Autowired
    SalarySetupRepository salarySetupRepository;
    @Override
    public void saveDeduction(SalarySetupDto salarySetupDto) {

        salarySetupRepository.save(salarySetupDtoTosalarySetup(salarySetupDto));
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
        salarySetup.setAnnualSalary(salarySetupDto.getAnnualSalary());

        return salarySetup;
    }

    public SalarySetupDto SalarySetupToSalarySetupDto(SalarySetup salarySetup)

    {

        SalarySetupDto salarySetupDto=new SalarySetupDto();
        salarySetupDto.setId(salarySetup.getId());
        salarySetupDto.setAnnualSalary(salarySetup.getAnnualSalary());
        salarySetupDto.setFinancialYear(salarySetup.getFinancialYear());
        salarySetupDto.setMonth(salarySetup.getMonth());
        salarySetupDto.setEmployeeId(salarySetup.getEmployeeId());

        return salarySetupDto;



    }

    public SalarySetupDto updateSalarySetup(SalarySetupDto salarySetupDto) {
        salarySetupRepository.save(salarySetupDtoTosalarySetup(salarySetupDto));
        return salarySetupDto;

    }
}
