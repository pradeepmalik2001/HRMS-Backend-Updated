package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.LeaveRecordRepository;
import com.ahom.hrms.entities.Employee;
import com.ahom.hrms.entities.LeaveRecord;
import com.ahom.hrms.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Scheduled(cron = "0 41 15 4 * *")
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
}
