package com.ecommerce.ecommerce.service;

import java.util.List;



import com.ecommerce.ecommerce.entity.Notification;


public interface NotificationService {

    Notification save(Notification notification);

    List<Notification> getNotifications(Long userId);

    Notification markAsRead(Long notificationId);

    void deleteNotification(Long notificationId);

}
