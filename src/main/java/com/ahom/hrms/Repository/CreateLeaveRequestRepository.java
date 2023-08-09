package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.CreateLeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories
public interface CreateLeaveRequestRepository extends JpaRepository<CreateLeaveRequest, String> {

    CreateLeaveRequest findByEmployeeIdAndStartDateAndEndDate(String employeeId,String startDate,String endDate);

    @Query(value= "Select * FROM create_leave_request o WHERE o.employee_id=:employeeId AND o.date BETWEEN :startDate AND :endDate AND o.status=:status",
            nativeQuery = true)
    List<CreateLeaveRequest> findByEmployeeIdAndStartDateAndEndDateAndStatus(@Param("employeeId") String employeeId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String status);

}
