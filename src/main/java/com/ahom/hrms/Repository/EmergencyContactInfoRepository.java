package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.EmergencyContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface EmergencyContactInfoRepository extends JpaRepository<EmergencyContactInfo, String> {


}
