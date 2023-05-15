package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Approve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ApproveRepository extends JpaRepository<Approve,Integer>
{

}
