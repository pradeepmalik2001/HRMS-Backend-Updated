package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.OverTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


@EnableJpaRepositories
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{

  Attendance findBySelectEmployee(String name);
  List  <Attendance> findBySelectEmployeeAndStatusAndDateBetween(
          @Param("selectEmployee") String selectEmployee,
          @Param("status")String status,
          @Param("date")Date startDate,
          @Param("endDate") Date endDate
      );


    @Query(value= "Select * FROM attendance o WHERE o.select_employee=:name AND o.date BETWEEN :startdate AND :enddate",
            nativeQuery = true)
    List<Attendance> findByNameAndDateRange(@Param("startdate") Date startdate,
                                          @Param("enddate")Date enddate
            , @Param("name") String name);

    @Query(value = "SELECT * FROM attendance ud WHERE ud.select_employee=:name " +
            "AND ud.date BETWEEN :startdate AND " +
            ":enddate AND status=:status" ,nativeQuery = true)
    List<Attendance> findByMonth(@Param("startdate") Date startdate,
                                 @Param("enddate")Date enddate,
                                 @Param("name") String name,
                                 @Param("status") String status);


    @Query(value = "SELECT count(*) FROM attendance ud WHERE ud.select_employee=:name " +
            "AND ud.date BETWEEN :startDate AND " +
            ":endDate AND status=:status",nativeQuery = true)
    Integer getOneSelectEmployee(@Param("startDate") Date startDate,
                                 @Param("endDate")Date endDate,
                                 @Param("name") String name,
                                 @Param("status") String status);

}
