package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.entities.WorkInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface WorkInformationRepository extends JpaRepository<WorkInformation, String>{
 //   WorkInformation findById(Integer  employeeId);
    WorkInformation findByBasicEmployee(int basicEmployee);

}
