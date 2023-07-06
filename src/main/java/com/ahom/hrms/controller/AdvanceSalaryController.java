package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.AdvanceSalary;
import com.ahom.hrms.service.AdvanceSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advance")
@CrossOrigin
public class AdvanceSalaryController
{
    @Autowired
    AdvanceSalaryService advanceSalaryService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveAdvanceSalary(@Valid @RequestBody AdvanceSalary advanceSalary)
    {
        return ResponseHandler.responseBuilder("Data Saved Successfully", HttpStatus.OK,advanceSalaryService.saveSalary(advanceSalary));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllSalary()
    {
        List<AdvanceSalary> advanceSalaries=advanceSalaryService.getAllSalary();
        return ResponseHandler.responseBuilder("Data Fetched Successfully",HttpStatus.OK,advanceSalaries);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAdvance(@Valid @RequestBody AdvanceSalary advanceSalary,@PathVariable String id)
    {
        return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,advanceSalaryService.updateSalary(advanceSalary,id));
    }

    @PutMapping("/updatePerMonth/{id}")
    public ResponseEntity<Object> updateAdvanecPerMonth(@Valid @RequestBody AdvanceSalary advanceSalary,@PathVariable String id)
    {
        return ResponseHandler.responseBuilder("Data Updated Successfully",HttpStatus.OK,advanceSalaryService.updatePerMonthDeduction(advanceSalary,id));
    }
}
