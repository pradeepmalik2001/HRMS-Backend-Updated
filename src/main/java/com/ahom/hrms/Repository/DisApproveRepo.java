package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.DisApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface DisApproveRepo extends JpaRepository<DisApprove,Integer>
{

}
