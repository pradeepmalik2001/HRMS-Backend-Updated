package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Integer>{


   public Role findByRoleName(String roleName);
}
