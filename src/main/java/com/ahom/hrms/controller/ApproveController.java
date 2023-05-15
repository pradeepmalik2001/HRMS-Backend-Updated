package com.ahom.hrms.controller;

import com.ahom.hrms.entities.Approve;
import com.ahom.hrms.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/approve")
public class ApproveController
{
    @Autowired
    ApproveService approveService;

    @PostMapping("/save")
    public ResponseEntity<Approve> saveApprove(@RequestBody Approve approve)
    {
        return new ResponseEntity<Approve>(approveService.saveApprove(approve),HttpStatus.CREATED);
    }
}
