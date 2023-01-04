package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{

}
