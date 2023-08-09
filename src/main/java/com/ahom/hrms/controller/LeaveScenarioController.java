package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.serviceimpl.LeaveScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/scenario")
public class LeaveScenarioController
{
    @Autowired
    LeaveScenarioService leaveScenarioService;

    @PostMapping("/save")
    public ResponseEntity<Object> approvedLeave(@RequestParam String name, @RequestParam String userName, @RequestParam String month, @RequestParam String employeeId) throws ParseException
    {
        return ResponseHandler.responseBuilder("Data", HttpStatus.OK,leaveScenarioService.LeaveApproved(name,userName,month,employeeId));
    }
 }
