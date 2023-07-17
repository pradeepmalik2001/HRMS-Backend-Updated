package com.ahom.hrms.service;

import com.ahom.hrms.entities.LeaveRecord;

public interface LeaveRecordService
{
    public LeaveRecord saveLeave(LeaveRecord leaveRecord);
    LeaveRecord updateLeaveRecord(LeaveRecord leaveRecord,int id);
}
