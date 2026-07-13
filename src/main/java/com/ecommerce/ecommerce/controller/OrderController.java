package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.PlaceOrderRequest;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ================= PLACE ORDER =================

    @PostMapping("/place")
    public Order placeOrder(
            @RequestBody PlaceOrderRequest request) {

        return orderService.placeOrder(request);
    }

    // ================= USER ORDERS =================

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(
            @PathVariable Long userId) {

        return orderService.getOrdersByUser(userId);
    }

    // ================= SELLER ORDERS =================

    @GetMapping("/seller/{sellerId}")
    public List<Order> getOrdersBySeller(
            @PathVariable Long sellerId) {

        return orderService.getOrdersBySeller(sellerId);
    }

    // ================= GET ORDER =================

    @GetMapping("/{orderId}")
    public Order getOrder(
            @PathVariable Long orderId) {

        return orderService.getOrderById(orderId);
    }

    // ================= GET ALL =================

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    // ================= UPDATE ORDER =================

    @PutMapping("/update/{orderId}")
    public Order updateOrder(

            @PathVariable Long orderId,

            @RequestBody Order order) {

        return orderService.updateOrder(orderId, order);
    }

    // ================= CANCEL ORDER =================

    @PutMapping("/cancel/{orderId}")
    public Order cancelOrder(
            @PathVariable Long orderId) {

        return orderService.cancelOrder(orderId);
    }

}