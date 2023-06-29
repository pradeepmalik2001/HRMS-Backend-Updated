package com.ahom.hrms.Repository;

//import java.sql.Date;
//import java.util.List;

import com.ahom.hrms.entities.ShiftManagement;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
//import org.springframework.data.repository.query.Param;



@EnableJpaRepositories
public interface AddShiftRepo extends JpaRepository<ShiftManagement,String>{
	
	List<ShiftManagement> findByUserName(String userName);

//	    @Query(value = "SELECT * FROM leave as l WHERE l.date >= :startDate AND l.date <= :endDate", nativeQuery = true)
//	    List<ShiftManagement> findByDateRange(@Param("startDate")Date startDate, @Param("endDate")Date endDate);


}