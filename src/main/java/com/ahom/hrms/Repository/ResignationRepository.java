package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Resignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ResignationRepository extends JpaRepository<Resignation,String>
{

}
