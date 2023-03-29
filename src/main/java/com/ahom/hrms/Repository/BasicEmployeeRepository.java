package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.BasicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories
public interface BasicEmployeeRepository extends JpaRepository<BasicEmployee, Integer>{



BasicEmployee findByEmployeeName(String employeeName);

	@Query (value = "SELECT * FROM basic_employee INNER JOIN banking_info on basic_employee.employee_id =banking_info.id where basic_employee.employee_id=:id",
			nativeQuery = true)
	List<BasicEmployee> findAllByDetails(@Param("id")int id);

}
