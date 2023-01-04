package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface ApplicationRepo  extends JpaRepository<Application, Integer>{

	
}
