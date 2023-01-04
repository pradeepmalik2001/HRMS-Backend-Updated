package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.LoanApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface LoanAppRepository extends JpaRepository<LoanApp,Integer> {

}