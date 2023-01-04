package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.BasicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface BasicEmployeeRepository extends JpaRepository<BasicEmployee, Integer>{
	
//	BasicEmployee findByEmployeeId(Integer employeeId);

}
