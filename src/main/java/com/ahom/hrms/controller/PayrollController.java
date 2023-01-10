package com.ahom.hrms.controller;
import java.util.List;

import com.ahom.hrms.dto.PayRollDto;
import com.ahom.hrms.serviceimpl.PayrollServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/payrolls")
@CrossOrigin
public class PayrollController {
	
	@Autowired
    PayrollServiceimpl payrollService;
	
	@PostMapping("/payroll")
	 public PayRollDto saveEmp(@RequestBody @Valid PayRollDto payrollDto)
 {


    payrollService.savePayroll(payrollDto);

     return payrollDto;

 }
	
	@GetMapping("/payroll")
public List<PayRollDto> getPayroll()
{
    List<PayRollDto> allPayroll = payrollService.getAllPayroll();
    return allPayroll ;
}

	 @GetMapping("/payroll/{payrollId}")
	 public PayRollDto getEmployeeById(@PathVariable("payrollId") int Id)
	    {
	        return payrollService.payrollById(Id);
	    }
	 	
	 
	 @DeleteMapping("/payroll/{payrollId}")
	    public void deleteEmp(@PathVariable("payrollId") int id){
	        payrollService.deletePayroll(id);
	    }


}