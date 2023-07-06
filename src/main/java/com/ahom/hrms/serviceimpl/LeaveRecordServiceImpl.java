package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.LeaveRecordRepository;
import com.ahom.hrms.dto.EmployeeLeaveCount;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.entities.LeaveRecord;
import com.ahom.hrms.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveRecordServiceImpl implements LeaveRecordService
{
    @Autowired
    LeaveRecordRepository leaveRecordRepository;

    @Override
    public LeaveRecord saveLeave(LeaveRecord leaveRecord)
    {
        return leaveRecordRepository.save(leaveRecord);
    }

    @Scheduled(cron = "0 40 17 6 * *")
    public void myScheduledMethod() {
        List<LeaveRecord> leaveRecords = leaveRecordRepository.findAll();
        for (LeaveRecord leaveRecord : leaveRecords) {
            if(leaveRecord.getLeaveLeft()>=0)
            {
                double totalLeaveRecord= leaveRecord.getLeaveLeft()+1.5;
                leaveRecord.setTotalLeave(totalLeaveRecord);
                leaveRecord.setLop(0);
                leaveRecord.setLeaveTaken(0);
                leaveRecord.setLeaveLeft(leaveRecord.getLeaveLeft()+1.5);
                leaveRecordRepository.save(leaveRecord);
            }
        }
        System.out.println("Method called on the 1st of every month.");
    }
    public List<EmployeeLeaveCount> getAllEmployeesLeaveCount() {
        List<LeaveRecord> leaveRecords = leaveRecordRepository.findAll();
        Map<String, Double> leaveCountMap = new HashMap<>();

        for (LeaveRecord leaveRecord : leaveRecords) {
            String employeeName = leaveRecord.getEmployeeName();
            double leaveTaken = leaveRecord.getLop();

            if (leaveCountMap.containsKey(employeeName)) {
                double existingLeaveCount = leaveCountMap.get(employeeName);
                double updatedLeaveCount = existingLeaveCount + leaveTaken;
                leaveCountMap.put(employeeName, updatedLeaveCount);
            } else {
                leaveCountMap.put(employeeName, leaveTaken);
            }
        }

        List<EmployeeLeaveCount> employeesLeaveCount = new ArrayList<>();

        for (Map.Entry<String, Double> entry : leaveCountMap.entrySet()) {
            String employeeName = entry.getKey();
            double leaveCount = entry.getValue();
            employeesLeaveCount.add(new EmployeeLeaveCount(employeeName, leaveCount));
        }

        return employeesLeaveCount;
    }

    public EmployeeLeaveCount getById(String id){
        LeaveRecord byId = leaveRecordRepository.getById(id);
        EmployeeLeaveCount employeeLeaveCount=new EmployeeLeaveCount();
        employeeLeaveCount.setEmployeeName(byId.getEmployeeName());
        employeeLeaveCount.setLeaveCount(byId.getLop());
        return employeeLeaveCount;
    }

}
