package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahom.hrms.entities.MonthlyPerformance;

@EnableJpaRepositories
public interface MonthlyPerformanceRepository extends JpaRepository<MonthlyPerformance, Integer>{

}
