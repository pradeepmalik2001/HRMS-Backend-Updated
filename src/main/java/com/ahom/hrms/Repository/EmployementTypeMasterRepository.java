package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.EmployementTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface EmployementTypeMasterRepository extends JpaRepository<EmployementTypeMaster, String>{

}
