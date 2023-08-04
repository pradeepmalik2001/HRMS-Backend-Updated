package com.ahom.hrms.service;

import com.ahom.hrms.entities.LeaveRecord;

import java.util.List;

public interface LeaveRecordService
{
     LeaveRecord saveLeave(LeaveRecord leaveRecord);

    LeaveRecord updateLeaveRecord(LeaveRecord leaveRecord,int id);

    List<LeaveRecord> getAll();

}
