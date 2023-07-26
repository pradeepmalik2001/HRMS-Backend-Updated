package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.Notification1Repository;
import com.ahom.hrms.entities.Notification1;
import com.ahom.hrms.service.Notification1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class Notification1ServiceImpl implements Notification1Service
{

    @Autowired
    Notification1Repository notification1Repository;

    @Override
    public Notification1 saveNotification(Notification1 notification1)
    {
        LocalTime localTime=LocalTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm");
        String format = formatter.format(localTime);
        System.out.println(format+ "localTime");
        notification1.setTime(format);
        return notification1Repository.save(notification1);
    }

    @Override
    public List<Notification1> getNotification(String employeeId)
    {
        List<Notification1> notification1s=notification1Repository.getByEmployeeId(employeeId);
        return notification1s;
    }

    @Override
    public Notification1 updateNotification(Notification1 notification1, int id)
    {
        Notification1 notification11=notification1Repository.findById(id).orElse(null);
        if(notification11!=null)
        {
            notification11.setStatus(notification1.isStatus());
            notification1Repository.save(notification11);
        }
        return notification1;
    }
}
