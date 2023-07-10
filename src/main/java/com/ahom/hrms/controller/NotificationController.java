package com.ahom.hrms.controller;import com.ahom.hrms.Response.ResponseHandler;import com.ahom.hrms.entities.Notification;import com.ahom.hrms.service.NotificationService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;@RestController@RequestMapping("/notification")public class NotificationController {    @Autowired    NotificationService notificationService;    @PostMapping("/save")    public ResponseEntity<Object> saveNotification(@RequestBody Notification notification){        return ResponseHandler.responseBuilder("Notification created", HttpStatus.OK                ,notificationService.saveNotification(notification));    }    @GetMapping("/getAll")    public  ResponseEntity<Object> getAllNotification(){        return ResponseHandler.responseBuilder("success",HttpStatus.OK                ,notificationService.getAllNotification());    }    @PostMapping("/update/{id}")    public ResponseEntity<Object> updateNotification(@PathVariable int id, @RequestBody Notification notification){        return ResponseHandler.responseBuilder("update successfully",HttpStatus.OK                ,notificationService.updateNotification(id,notification));    }}