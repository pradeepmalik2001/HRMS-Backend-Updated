package com.ahom.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.entities.Performance;
import com.ahom.hrms.service.PerformanceService;

@RestController
@RequestMapping("/")
public class PerformanceController{ 
     @Autowired
     private PerformanceService performanceService;
    @PostMapping("performance")
    public Performance addPerformance(@RequestBody Performance performance){
        return performanceService.addperform(performance);
    }
}