package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Notification1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface Notification1Repository extends JpaRepository<Notification1,Integer>
{
    List<Notification1> getByEmployeeId(String employeeId);
}
