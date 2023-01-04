package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer>{
	
	UserMaster findByUserName(String userName);

}
