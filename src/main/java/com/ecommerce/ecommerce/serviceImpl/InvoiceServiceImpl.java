package com.ecommerce.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.InvoiceDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public InvoiceDTO getInvoice(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                new RuntimeException("Order Not Found"));

        InvoiceDTO invoice = new InvoiceDTO();

        invoice.setOrderId(order.getOrderId());
        invoice.setOrderDate(order.getOrderDate());

        invoice.setCustomerName(
                order.getUser().getFirstName() + " "
                + order.getUser().getLastName());

        invoice.setCustomerEmail(
                order.getUser().getEmail());

        invoice.setCustomerMobile(
                order.getUser().getMobile());

        invoice.setSellerName(
                order.getSellerName());

        invoice.setShopName(
                order.getShopName());

        invoice.setPaymentMethod(
                order.getPaymentMethod());

        invoice.setPaymentStatus(
                order.getPaymentStatus());

        invoice.setDeliveryName(
                order.getDeliveryName());

        invoice.setDeliveryAddress(
                order.getDeliveryAddress());

        invoice.setDeliveryCity(
                order.getDeliveryCity());

        invoice.setDeliveryState(
                order.getDeliveryState());

        invoice.setDeliveryPincode(
                order.getDeliveryPincode());

        invoice.setTotalAmount(
                order.getTotalAmount());

        invoice.setOrderItems(
                order.getOrderItems());

        return invoice;
    }

}