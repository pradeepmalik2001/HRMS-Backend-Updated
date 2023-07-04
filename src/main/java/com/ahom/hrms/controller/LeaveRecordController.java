package com.ahom.hrms.controller;

import com.ahom.hrms.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveRecordController
{
    @Autowired
    LeaveRecordService leaveRecordService;


}
