package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.PlaceOrderRequest;
import com.ecommerce.ecommerce.entity.Order;

public interface OrderService {

	Order placeOrder(PlaceOrderRequest request);
	
	    List<Order> getOrdersByUser(Long userId);

	    List<Order> getOrdersBySeller(Long sellerId);

	    Order getOrderById(Long orderId);

	    Order cancelOrder(Long orderId);

	
}
