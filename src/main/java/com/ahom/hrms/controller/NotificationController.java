package com.ahom.hrms.controller;
//package com.ahom.hrms.controller;
//
//import com.ahom.hrms.entities.NotificationResponse;
//import com.ahom.hrms.entities.PushNotification;
//import com.ahom.hrms.serviceimpl.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class PushNotificationController {
//    @Autowired
//
//    private NotificationService pushNotificationService;
//
//    public PushNotificationController(NotificationService pushNotificationService) {
//        this.pushNotificationService = pushNotificationService;
//    }
//
//    @PostMapping("/notification/token")
//    public ResponseEntity sendTokenNotification(@RequestBody PushNotification request) {
//        pushNotificationService.sendPushNotificationToToken(request);
//        System.out.println("Message"+request);
//        return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }
//}

import com.ahom.hrms.entities.PushNotification;
import com.ahom.hrms.serviceimpl.FirebaseNotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final FirebaseNotificationService notificationService;

    @Autowired
    public NotificationController(FirebaseNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody PushNotification request) {
        try {
            notificationService.sendNotification(request.getToken(), request.getTitle(), request.getBody());
            return ResponseEntity.ok("Notification sent successfully.");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send notification: " + e.getMessage());
        }
    }
}