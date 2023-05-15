package com.ahom.hrms.controller;

import com.ahom.hrms.entities.DisApprove;
import com.ahom.hrms.service.DisApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/disApprove")
public class DisApproveController
{
    @Autowired
    DisApproveService disApproveService;

    @PostMapping("/save")
    public ResponseEntity<DisApprove> saveDisapprove(@RequestBody DisApprove disApprove)
    {
        return new ResponseEntity<>(disApproveService.saveDisapprove(disApprove), HttpStatus.CREATED);
    }
}
