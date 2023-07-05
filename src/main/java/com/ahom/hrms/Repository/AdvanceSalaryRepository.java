package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.AdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface AdvanceSalaryRepository extends JpaRepository<AdvanceSalary,String>
{

}
