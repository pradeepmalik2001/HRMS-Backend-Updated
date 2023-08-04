package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.LeaveRecord;
import com.ahom.hrms.service.LeaveRecordService;
import com.ahom.hrms.serviceimpl.LeaveRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class LeaveRecordController
{
    @Autowired
    LeaveRecordServiceImpl leaveRecordService;


    @GetMapping("/get")
    public ResponseEntity<Object>get(){
        return ResponseHandler.responseBuilder("ddd", HttpStatus.OK
                ,leaveRecordService.getAllEmployeesLeaveCount());
    }

    @GetMapping("/getById")
    public ResponseEntity<Object>getById(@RequestParam String employeeId,@RequestParam String leaveRecordOfMonth){
        String upperCase = leaveRecordOfMonth.toUpperCase();
        return ResponseHandler.responseBuilder("Record for EmployeeId : "+employeeId,HttpStatus.OK
                ,leaveRecordService.getById(employeeId,upperCase));
    }

    @PostMapping("/save")
    public ResponseEntity<Object>saveLeaveRecord(@RequestBody LeaveRecord leaveRecord){
        LeaveRecord leaveRecord1=new LeaveRecord();

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM yyyy");
        String format = String.format(leaveRecord.getLeaveRecordOfMonth(), formatter);

        leaveRecord1.setLeaveRecordOfMonth(format.toUpperCase());
        leaveRecord1.setEmployeeId(leaveRecord.getEmployeeId());
        leaveRecord1.setEmployeeName(leaveRecord.getEmployeeName());
        leaveRecord1.setLeaveLeft(leaveRecord.getTotalLeave());
        leaveRecord1.setTotalLeave(leaveRecord.getTotalLeave());
        leaveRecord1.setLop(0);
        leaveRecord1.setLeaveTaken(0);

        return
                ResponseHandler.responseBuilder("Saved Successfully",
                        HttpStatus.OK,leaveRecordService.saveLeave(leaveRecord1));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object>getAll(){
        return ResponseHandler.responseBuilder("fetched",HttpStatus.OK,leaveRecordService.getAll());
    }


}
