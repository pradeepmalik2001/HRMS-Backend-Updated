package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.BankingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface BankingInfoRepository extends JpaRepository<BankingInfo, String>{

}
