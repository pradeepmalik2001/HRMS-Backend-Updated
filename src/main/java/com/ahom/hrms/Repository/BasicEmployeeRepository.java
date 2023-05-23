package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.BasicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@EnableJpaRepositories
public interface BasicEmployeeRepository extends JpaRepository<BasicEmployee, Integer>{



BasicEmployee findByMobile(String mobile);

BasicEmployee findByAadhaarNumberAndPanNumberAndPfnumberAndMobileAndEmail( String aadhaarNumber ,String panNumber,
													   String pfnumber,String mobile,String email);

	@Query (value = "SELECT * FROM basic_employee INNER JOIN banking_info on basic_employee.employee_id =banking_info.id where basic_employee.employee_id=:id",
			nativeQuery = true)
	List<BasicEmployee> findAllByDetails(@Param("id")int id);


	BasicEmployee findByDob(LocalDate dob);

}
