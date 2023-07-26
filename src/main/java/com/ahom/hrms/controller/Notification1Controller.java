package com.ahom.hrms.controller;

import com.ahom.hrms.Response.ResponseHandler;
import com.ahom.hrms.entities.Notification1;
import com.ahom.hrms.service.Notification1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification1")
@CrossOrigin
public class Notification1Controller
{
    @Autowired
    Notification1Service notification1Service;

    @PostMapping("/save")
    public ResponseEntity<Object> saveNotification(Notification1 notification1)
    {
        return ResponseHandler.responseBuilder("Notification Saved Successfully", HttpStatus.OK,notification1Service.saveNotification(notification1));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllNotification()
    {
        return ResponseHandler.responseBuilder("Notification Fetched Successfully",HttpStatus.OK,notification1Service.getAllNotification());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateNotification(Notification1 notification1,int id)
    {
        return ResponseHandler.responseBuilder("Notification Updated Successfully",HttpStatus.OK,notification1Service.updateNotification(notification1,id));
    }
}
