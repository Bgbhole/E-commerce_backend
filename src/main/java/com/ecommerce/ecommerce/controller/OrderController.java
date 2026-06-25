package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

	
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/seller/{sellerId}")
    public List<Order> getSellerOrders(@PathVariable Long sellerId) {

        return orderRepository.findBySellerSellerId(sellerId);

    }
    
 
}
