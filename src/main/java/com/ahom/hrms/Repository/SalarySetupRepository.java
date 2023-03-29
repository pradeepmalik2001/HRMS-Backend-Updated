package com.ahom.hrms.Repository;


import com.ahom.hrms.dto.EmpAllowance;
import com.ahom.hrms.entities.OverTime;
import com.ahom.hrms.entities.SalarySetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface SalarySetupRepository extends JpaRepository<SalarySetup,Integer> {


    SalarySetup findById(int id);

//    @Query(value = "SELECT annual_salary/100*15 as "hra" from  hrms2.salary_setup    where emp_id =1 ,nativeQuery = true )
//    //@Query(value= "SELECT * FROM overtime o WHERE o.select_employee=:name AND o.date BETWEEN :startdate AND :enddate", nativeQuery = true)
//    List<OverTime> findByEmployeeId(@PathVariable int employeeId);

//    @Query(value="select * From salary_setup s WHERE s.emp_id=:n", nativeQuery = true)
//    public  List<SalarySetup> findByEmployeeId(@Param("n") int employeeId);

//    @Query(value="Select * from hrms2.salarysetup s INNER JOIN hrms2.basic_employee b on b.employee_id=s.emp_id AND s.emp_id=:n", nativeQuery = true)
//   @Query(value="Select * from hrms2.salary_setup s WHERE s.emp_id=:n", nativeQuery = true)
//    public  SalarySetup findByEmployeeId(@Param("n") int employeeId);
@Query(value="Select  *  from hrms2.basic_employee  LEFT JOIN hrms2.salary_setup on hrms2.basic_employee.employee_id = hrms2.salary_setup.id where hrms2.basic_employee.employee_id=:n", nativeQuery = true)
//  @Query(value="SELECT * FROM hrms2.salary_setup CROSS JOIN hrms2.basic_employee AND s.emp_id=:n", nativeQuery = true)
public  SalarySetup findByEmployeeId(@Param("n") int employeeId);

//    @Query(value="Select * from hrms2.employee_allowances s WHERE s.emp_id=:n", nativeQuery = true)
//   public  SalarySetup findByAllowanceId(@Param("n") int employeeId);

    @Query(value="Select hrms2.employee_allowances.ammount FROM hrms2.employee_allowances LEFT JOIN hrms2.salary_setup on hrms2.salary_setup.id= hrms2.employee_allowances.id where hrms2.employee_allowances.employee_id=:n", nativeQuery = true)
            public EmpAllowance findByAllowanceId(@Param("n") int employeeId);
}
