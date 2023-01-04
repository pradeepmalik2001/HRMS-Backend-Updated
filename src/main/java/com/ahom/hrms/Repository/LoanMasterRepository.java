package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.LoanMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface LoanMasterRepository extends JpaRepository<LoanMaster, Integer> {

}
