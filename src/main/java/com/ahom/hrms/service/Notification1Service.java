package com.ahom.hrms.service;

import com.ahom.hrms.entities.Notification1;

import java.util.List;

public interface Notification1Service
{
    Notification1 saveNotification(Notification1 notification1);

    List<Notification1> getNotification(String employeeId);

    Notification1 updateNotification(Notification1 notification1,int id);
}
