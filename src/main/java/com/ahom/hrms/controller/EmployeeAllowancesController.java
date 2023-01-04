package com.ahom.hrms.controller;

import java.util.List;

import com.ahom.hrms.dto.EmployeeAllowancesDto;
import com.ahom.hrms.serviceimpl.EmployeeAllowancesServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employeeAllowances")
@CrossOrigin
public class EmployeeAllowancesController {
	
	@Autowired
    EmployeeAllowancesServiceimpl employeeAllowancesService;
	
	@PostMapping("/employeeAllowances")
	 public EmployeeAllowancesDto saveEmp(@RequestBody @Valid EmployeeAllowancesDto employeeAllowancesDto)
 {
    employeeAllowancesService.saveEmployeeAllowances(employeeAllowancesDto);

     return employeeAllowancesDto;

 }
	
	@GetMapping("/employeeAllowances")
public List<EmployeeAllowancesDto> getEmplpoyeeAllowances()
{
    List<EmployeeAllowancesDto> allEmployeeAllowances = employeeAllowancesService.getAllEmployeeAllowances();
    return allEmployeeAllowances ;
}

	 @GetMapping("/employeeAllowances/{employeeAllowancesId}")
	 public EmployeeAllowancesDto getEmployeeAllowancesById(@PathVariable("EmployeeAllowancesId") int Id)
	    {
	        return employeeAllowancesService.employeeAllowancesById(Id);
	    }
	 	
	 
	 @DeleteMapping("/employeeAllowances/{employeeAllowancesId}")
	    public void deleteEmp(@PathVariable("employeeId") int id){
	        employeeAllowancesService.deleteEmployeeAllowance(id);
	    }

	
	






	
	

}