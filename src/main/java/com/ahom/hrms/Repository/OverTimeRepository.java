package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.OverTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@EnableJpaRepositories
public interface OverTimeRepository extends JpaRepository<OverTime, Integer> {

//    @Query(value = "SELECT * FROM Overtime ud WHERE ud.date BETWEEN :startdate AND :enddate AND ud.select_employee=:name" ,nativeQuery = true )
    @Query(value= "SELECT * FROM overtime o WHERE o.select_employee=:name AND o.date BETWEEN :startdate AND :enddate", nativeQuery = true)
    List <OverTime> findByNameAndDateRange( @Param("startdate") String startdate,
                                     @Param("enddate")String enddate
                                , @Param("name") String name);


}
