package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.AddApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface AddApprovalRepository extends JpaRepository<AddApproval, String>{

}
