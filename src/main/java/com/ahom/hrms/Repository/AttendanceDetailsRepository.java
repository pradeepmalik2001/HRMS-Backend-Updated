package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahom.hrms.entities.AttendanceDetails;

@EnableJpaRepositories
public interface AttendanceDetailsRepository extends JpaRepository<AttendanceDetails, String>{

}
