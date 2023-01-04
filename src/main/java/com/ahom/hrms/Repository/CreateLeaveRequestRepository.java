package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.CreateLeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface CreateLeaveRequestRepository extends JpaRepository<CreateLeaveRequest, Double> {

}
