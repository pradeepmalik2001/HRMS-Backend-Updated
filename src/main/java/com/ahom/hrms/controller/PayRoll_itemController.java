package com.ahom.hrms.controller;


import com.ahom.hrms.dto.Payroll_itemDto;
import com.ahom.hrms.serviceimpl.Payroll_itemServicesimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/payrollitem")
@RestController
@CrossOrigin
public class PayRoll_itemController {
	
	@Autowired
    Payroll_itemServicesimpl payroll_itemServices;
	
	
	@PostMapping("/payrollitem")
	public ResponseEntity<Payroll_itemDto> savepayrollitems(@RequestBody @Valid Payroll_itemDto payroll_Itemdto){
		payroll_itemServices.savePayrollitem(payroll_Itemdto);
		
		
		return new ResponseEntity<>(payroll_Itemdto,HttpStatus.CREATED);
		
	}

	    @DeleteMapping("/payrollitem/{payrollitemId}")
	    public void deleteAllo(@PathVariable("payrollitemId") int id){
	      
	    	
	    	payroll_itemServices.deletePayrollitem(id);
	    }
	@GetMapping("/payrollitem")
	public List<Payroll_itemDto> getpayPayroll_item()
	{
		List<Payroll_itemDto> allitem = payroll_itemServices.getAllpayrollitem();
		return allitem ;
	}





	@PutMapping("/payrollitem")
	    public ResponseEntity<Payroll_itemDto> updateAll(@RequestBody Payroll_itemDto payroll_itemDto){
	    	payroll_itemServices.updatePayrollitem(payroll_itemDto);
	    	 return new ResponseEntity<>(payroll_itemDto,HttpStatus.CREATED);
	    	
	    }
	      
	    }	



 
		
	
	
