package com.ahom.hrms.Repository;

import com.ahom.hrms.entities.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushNotificationRepo extends JpaRepository<PushNotification,Integer> {
}
