package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
	
	  List<Order> findBySellerSellerId(Long sellerId);
}