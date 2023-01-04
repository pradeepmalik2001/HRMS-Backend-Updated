package com.ahom.hrms.controller;

import com.ahom.hrms.dto.EmployeeDeductionDto;
import com.ahom.hrms.serviceimpl.EmployeeDeductionServicesimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeededuction")
@CrossOrigin
public class EmployeedeductionController {
	

	   @Autowired
       EmployeeDeductionServicesimpl deductinServices;

	@PostMapping("/empdeduction")
public ResponseEntity<EmployeeDeductionDto>saveDeduction(@RequestBody @Valid EmployeeDeductionDto empeloyeDeductiondto){
	 
	 deductinServices.saveEmp(empeloyeDeductiondto);
	 return new ResponseEntity<>(empeloyeDeductiondto,HttpStatus.CREATED); 
}
@DeleteMapping("/empdeduction/{id}")
public void deleteDeduction(@PathVariable("id")int id ) {
	 
	 deductinServices.deleteempdeduction(id);
	}
	@GetMapping("/employeededution")
	public List<EmployeeDeductionDto> getempdedution()
	{
		List<EmployeeDeductionDto> allDeduction = deductinServices.getAllempdeduction();
		return allDeduction ;
	}

//@PutMapping("/empdeduction") 
//public ResponseEntity<Deductiondto>updateall(@RequestBody Deductiondto deductionDto){
	 
//	 deductinServices.upd(deductionDto);
//	 return new ResponseEntity<>(deductionDto,HttpStatus.CREATED);
}






