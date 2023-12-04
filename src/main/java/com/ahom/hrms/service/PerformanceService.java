package com.ahom.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.PerformanceRepository;
import com.ahom.hrms.entities.Performance;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance addperform(Performance performance){
          return performanceRepository.save(performance);
    }
}
