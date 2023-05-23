package com.ahom.hrms.Repository;


import com.ahom.hrms.entities.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
public interface LeaveTypeRepository extends JpaRepository<LeaveType,Integer> {

}
