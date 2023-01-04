package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.dto.CreateLeaveRequestDto;
import com.ahom.hrms.dto.MonthlyPerformanceDto;
import com.ahom.hrms.serviceimpl.MonthlyPerformanceServiceImpl;

@RestController
@RequestMapping("/MonthlyPerformance")
public class MonthlyPerformanceController {
	
	@Autowired
	MonthlyPerformanceServiceImpl monthlyPerformanceService;
	
	
	@PostMapping("/post")
    public ResponseEntity<MonthlyPerformanceDto> saveEmp(@RequestBody MonthlyPerformanceDto monthlyPerformanceDto)
    {
        monthlyPerformanceService.saveMonthlyPerformance(monthlyPerformanceDto);

        return new ResponseEntity<>(monthlyPerformanceDto, HttpStatus.CREATED);
      
    }
	
	@GetMapping("/get")
    public ResponseEntity<List<MonthlyPerformanceDto>> getMonthlyPerformance()
    {
        List<MonthlyPerformanceDto> allMonthlyPerformance = monthlyPerformanceService.getAllMonthlyPerformance();
   
        return ResponseEntity.of(Optional.of(allMonthlyPerformance));

    }
	
	 @DeleteMapping("/delete/{deleteId}")
	    public void deleteEmp(@PathVariable("monthlyPerformanceId") int id){
		 monthlyPerformanceService.deleteMonthlyPerformance(id);
	    }
	 
	 @PutMapping("/put")
	    public ResponseEntity<MonthlyPerformanceDto> updateEmp(@RequestBody MonthlyPerformanceDto monthlyPerformanceDto)
	    {
		 monthlyPerformanceService.updateMonthlyPerformance(monthlyPerformanceDto);
	        return new ResponseEntity<>(monthlyPerformanceDto, HttpStatus.ACCEPTED);
	    }

}
