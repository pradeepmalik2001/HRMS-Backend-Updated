package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.PushNotificationRepo;
import com.ahom.hrms.entities.PushNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class NotificationService {
    @Autowired
    PushNotificationRepo pushNotificationRepo;
    private Logger logger= LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    private FCMService fcmService;

    public NotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }
    public void sendPushNotificationToToken(PushNotification notification){
        try {
            fcmService.sendMessageToToken(notification);
            pushNotificationRepo.save(notification);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
