package com.ahom.hrms.controller;

import com.ahom.hrms.entities.NotificationResponse;
import com.ahom.hrms.entities.PushNotification;
import com.ahom.hrms.serviceimpl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushNotificationController {
    @Autowired

    private NotificationService pushNotificationService;

    public PushNotificationController(NotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotification request) {
        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("Message"+request);
        return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
}
