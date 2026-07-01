package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.SellerDashboardDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.enums.TrackingStatus;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class SellerDashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/seller/{sellerId}")
    public SellerDashboardDTO getDashboard(
            @PathVariable Long sellerId) {

        SellerDashboardDTO dto = new SellerDashboardDTO();

        List<Product> products =
                productRepository.findBySellerSellerId(sellerId);

        List<Order> orders =
                orderRepository.findBySellerSellerId(sellerId);

        dto.setTotalProducts(products.size());

        dto.setTotalOrders(orders.size());

        double revenue = 0;
        double profit = 0;

        for (Order order : orders) {

            if (order.getStatus() == TrackingStatus.CANCELLED) {
                continue;
            }

            revenue += order.getTotalAmount();

            for (OrderItem item : order.getOrderItems()) {

                profit += item.getProfit()
                        * item.getQuantity();
            }
        }
        dto.setRevenue(revenue);

        dto.setProfit(profit);

        return dto;
    }
}
