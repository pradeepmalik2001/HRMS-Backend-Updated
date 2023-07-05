package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.service.LeaveRecordService;
import com.ahom.hrms.serviceimpl.LeaveRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("record")
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
    public ResponseEntity<Object>getById(@RequestParam String id){
        return ResponseHandler.responseBuilder("sssssss",HttpStatus.OK,leaveRecordService.getById(id));
    }


}
