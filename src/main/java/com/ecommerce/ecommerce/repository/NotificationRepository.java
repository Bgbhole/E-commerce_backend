package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

}
