package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.OverTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


@EnableJpaRepositories
public interface OverTimeRepository extends JpaRepository<OverTime, Integer> {

    OverTime findByUserName(String userName);



//    @Query(value = "SELECT * FROM Overtime ud WHERE ud.date BETWEEN :startdate AND :enddate AND ud.select_employee=:name" ,nativeQuery = true )
    @Query(value= "SELECT * FROM overtime o WHERE o.user_name=:userName AND o.date BETWEEN :startdate AND :enddate", nativeQuery = true)
    List <OverTime> findByUserNameAndDateRange(@Param("startdate") Date startdate,
                                               @Param("enddate")Date enddate
                                , @Param("userName") String userName);


}
