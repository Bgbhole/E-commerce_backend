package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(
            @PathVariable Long userId){

        return notificationService.getNotifications(userId);

    }

    @PutMapping("/read/{notificationId}")
    public Notification markRead(
            @PathVariable Long notificationId){

        return notificationService.markAsRead(notificationId);

    }

    @DeleteMapping("/{notificationId}")
    public String delete(
            @PathVariable Long notificationId){

        notificationService.deleteNotification(notificationId);

        return "Notification Deleted Successfully";

    }

}