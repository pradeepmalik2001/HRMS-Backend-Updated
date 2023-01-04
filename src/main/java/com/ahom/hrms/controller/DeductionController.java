package com.ahom.hrms.controller;

import com.ahom.hrms.dto.DeductionDto;
import com.ahom.hrms.serviceimpl.DeductionServicesimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deductions")
@CrossOrigin
public class DeductionController {
	@Autowired
    DeductionServicesimpl deductinServices;
 @PostMapping("/deduction")
 public ResponseEntity<DeductionDto>saveDeduction(@RequestBody @Valid DeductionDto deductionDto){
	 
	 deductinServices.saveDeduction(deductionDto);
	 return new ResponseEntity<>(deductionDto,HttpStatus.CREATED); 
 }
 @DeleteMapping("/deduction/{id}")
 public void deleteDeduction(@PathVariable("id")int id ) {
	 
	 deductinServices.deleteDeduction(id);	 
	 
 }
 @PutMapping("/deduction") 
 public ResponseEntity<DeductionDto>updateall(@RequestBody DeductionDto deductionDto){
	 
	 deductinServices.updateDeduction(deductionDto);
	 return new ResponseEntity<>(deductionDto,HttpStatus.CREATED);
 }
    @GetMapping("/deductions")
    public List<DeductionDto> getdeduction()
    {
        List<DeductionDto> allDeduction = deductinServices.getAllDeduction();
        return allDeduction ;
    }
 
 
 
}
