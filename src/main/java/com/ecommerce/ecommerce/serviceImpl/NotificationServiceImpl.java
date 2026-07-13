package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.service.NotificationService;

@Service
public class NotificationServiceImpl  implements NotificationService {

	@Override
	public Notification save(Notification notification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotifications(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification markAsRead(Long notificationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNotification(Long notificationId) {
		// TODO Auto-generated method stub
		
	}

}
