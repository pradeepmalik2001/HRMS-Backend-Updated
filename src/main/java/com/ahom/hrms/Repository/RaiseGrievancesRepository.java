package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.RaiseGrievances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahom.hrms.entities.RaiseGrievances;

@EnableJpaRepositories
public interface RaiseGrievancesRepository extends JpaRepository<RaiseGrievances, String> {

}
