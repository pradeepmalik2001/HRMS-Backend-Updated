package com.ahom.hrms.controller;

import com.ahom.hrms.dto.SalarySetupDto;
import com.ahom.hrms.entities.SalarySetup;
import com.ahom.hrms.serviceimpl.SalarySetupServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/salary")
@CrossOrigin
public class SalarySetupController {

    @Autowired
    SalarySetupServiceimpl salarySetupService;

    @PostMapping("/salary1")
    public ResponseEntity<SalarySetup> saveSalary(@RequestBody @Valid SalarySetup salarySetupDto){

        salarySetupService.saveDeduction(salarySetupDto);
        return new ResponseEntity<>(salarySetupDto, HttpStatus.CREATED);
    }
    @GetMapping("/salary2")
    public List<SalarySetupDto> getSalary()
    {
        List<SalarySetupDto> all = salarySetupService.getAll();
        return all ;
    }
    @PutMapping("/salary3")
    public ResponseEntity<SalarySetupDto>updateall(@RequestBody SalarySetupDto salarySetupDto){

        salarySetupService.updateSalarySetup(salarySetupDto);
        return new ResponseEntity<>(salarySetupDto,HttpStatus.CREATED);
    }
    @DeleteMapping("/salary4/{SalaryId}")
    public void deleteSal(@PathVariable("SalaryId") int id){

        salarySetupService.deleteSalary(id);
    }

    @PostMapping("/month")
    public double monthly(@RequestParam int employeeId)
    {
       return salarySetupService.salaryCalculation(employeeId);

    }
    @GetMapping("/pf")
    public double pf(@RequestParam int employeeId)
    {
        return salarySetupService.pfCalculation(employeeId);

    }
    @GetMapping("/hra")
    public double hra(@RequestParam int employeeId)
    {

        return salarySetupService.hraCalculation(employeeId);
    }
    @GetMapping("/gross")
    public double grossEarn(@RequestParam int employeeId)
    {

        return salarySetupService.grossEarning(employeeId);
    }

}
