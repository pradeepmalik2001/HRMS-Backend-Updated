package com.ahom.hrms.controller;

import com.ahom.hrms.dto.SalarySetupDto;
import com.ahom.hrms.entities.SalarySetup;
import com.ahom.hrms.serviceimpl.SalarySetupServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/salary")
    public ResponseEntity<SalarySetup> saveSalary(@RequestBody @Valid SalarySetup salarySetupDto){

        salarySetupService.saveDeduction(salarySetupDto);
        return new ResponseEntity<>(salarySetupDto, HttpStatus.CREATED);
    }
    @GetMapping("/salary")
    public List<SalarySetupDto> getSalary()
    {
        List<SalarySetupDto> all = salarySetupService.getAll();
        return all ;
    }
    @PutMapping("/salary")
    public ResponseEntity<SalarySetupDto>updateall(@RequestBody SalarySetupDto salarySetupDto){

        salarySetupService.updateSalarySetup(salarySetupDto);
        return new ResponseEntity<>(salarySetupDto,HttpStatus.CREATED);
    }
    @DeleteMapping("/salary/{SalaryId}")
    public void deleteSal(@PathVariable("SalaryId") int id){

        salarySetupService.deleteSalary(id);
    }
}
