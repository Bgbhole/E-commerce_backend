package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.ReturnRequestDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.ReturnRequest;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.OrderItemRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ReturnRepository;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.ReturnService;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public ReturnRequest createReturn(ReturnRequestDTO dto) {

        Order order =
                orderRepository.findById(dto.getOrderId())
                .orElseThrow(() ->
                new RuntimeException("Order Not Found"));

        OrderItem orderItem =
                orderItemRepository.findById(dto.getOrderItemId())
                .orElseThrow(() ->
                new RuntimeException("Order Item Not Found"));

        User user =
                userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                new RuntimeException("User Not Found"));

        Seller seller = order.getSeller();

        ReturnRequest request = new ReturnRequest();

        request.setOrder(order);
        request.setOrderItem(orderItem);
        request.setUser(user);
        request.setSeller(seller);
        request.setReason(dto.getReason());
        request.setDescription(dto.getDescription());
        request.setStatus("PENDING");

        return returnRepository.save(request);
    }

    @Override
    public List<ReturnRequest> getAllReturns() {
        return returnRepository.findAll();
    }

    @Override
    public List<ReturnRequest> getUserReturns(Long userId) {
        return returnRepository.findByUserId(userId);
    }

    @Override
    public List<ReturnRequest> getSellerReturns(Long sellerId) {
        return returnRepository.findBySellerSellerId(sellerId);
    }

    @Override
    public ReturnRequest approveReturn(Long returnId) {

        ReturnRequest request =
                returnRepository.findById(returnId)
                .orElseThrow(() ->
                new RuntimeException("Return Not Found"));

        request.setStatus("APPROVED");

        return returnRepository.save(request);
    }

    @Override
    public ReturnRequest rejectReturn(Long returnId) {

        ReturnRequest request =
                returnRepository.findById(returnId)
                .orElseThrow(() ->
                new RuntimeException("Return Not Found"));

        request.setStatus("REJECTED");

        return returnRepository.save(request);
    }

}