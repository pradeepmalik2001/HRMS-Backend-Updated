package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.ResignationRepository;
import com.ahom.hrms.entities.Notification;
import com.ahom.hrms.entities.Resignation;
import com.ahom.hrms.service.NotificationService;
import com.ahom.hrms.service.ResignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResignationServiceImpl implements ResignationService
{
    @Autowired
    ResignationRepository resignationRepository;
    @Autowired
    NotificationService notificationService;

    @Override
    public Resignation postResignation(Resignation resignation)
    {
        Notification notification=new Notification();
        notification.setMessage("New Resignation Request");
        notificationService.saveNotification(notification);
        return resignationRepository.save(resignation);
    }

    @Override
    public List<Resignation> fetchResignation()
    {
        return resignationRepository.findAll();
    }
}
