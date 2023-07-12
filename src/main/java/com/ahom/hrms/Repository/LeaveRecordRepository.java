package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.LeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface LeaveRecordRepository extends JpaRepository<LeaveRecord,String>
{
    LeaveRecord findByEmployeeIdAndLeaveRecordOfMonth(String employeeId,String leaveRecordOfMonth);

}
