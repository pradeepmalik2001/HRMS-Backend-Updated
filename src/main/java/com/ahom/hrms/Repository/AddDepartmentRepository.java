package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.AddDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface AddDepartmentRepository extends JpaRepository<AddDepartment, Integer>{

}
