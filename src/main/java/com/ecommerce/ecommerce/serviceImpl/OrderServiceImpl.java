package com.ecommerce.ecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.PlaceOrderRequest;
import com.ecommerce.ecommerce.entity.Address;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.OrderTracking;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.enums.TrackingStatus;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.OrderItemRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.OrderTrackingRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.OrderService;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.OrderTracking;
import com.ecommerce.ecommerce.entity.Product;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderTrackingRepository orderTrackingRepository;

    @Override
    public Order placeOrder(PlaceOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address deliveryAddress = addressRepository
                .findById(request.getDeliveryAddressId())
                .orElseThrow(() -> new RuntimeException("Delivery Address not found"));

        System.out.println("User ID = " + user.getId());

        List<Cart> cartItems = cartRepository.findByUserId(user.getId());

        System.out.println("Cart Size = " + cartItems.size());

        for (Cart c : cartItems) {
            System.out.println(
                "Cart -> user=" + c.getUser().getId()
                + ", product=" + c.getProduct().getProductId()
                + ", qty=" + c.getQuantity()
            );
        }

        // Continue using the same cartItems variable
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

       
        Order order = new Order();

        order.setUser(user);
        order.setSeller(cartItems.get(0).getProduct().getSeller());

        order.setOrderDate(LocalDateTime.now());

        order.setStatus(TrackingStatus.ORDER_CONFIRMED);

        order.setPaymentMethod(request.getPaymentMethod());

        order.setPaymentStatus("SUCCESS");

        order.setTrackingNumber(System.currentTimeMillis());

        order.setDeliveryName(deliveryAddress.getFullName());
        order.setDeliveryMobile(deliveryAddress.getMobile());
        order.setDeliveryAddress(deliveryAddress.getFullAddress());
        order.setDeliveryCity(deliveryAddress.getCity());
        order.setDeliveryState(deliveryAddress.getState());
        order.setDeliveryPincode(deliveryAddress.getPincode());

        double totalAmount = 0;

        for (Cart cart : cartItems) {
            totalAmount += cart.getProduct().getFinalPrice()
                    * cart.getQuantity();
        }

        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);
        
        for (Cart cart : cartItems) {

        	OrderItem orderItem = new OrderItem();

        	Product product = cart.getProduct();

        	orderItem.setOrder(order);

        	// Keep reference to original product
        	orderItem.setProduct(product);

        	// =============================
        	// Product Snapshot
        	// =============================
        	orderItem.setProductName(product.getProductName());
        	orderItem.setBrand(product.getBrand());
        	orderItem.setCategory(product.getCategory());
        	orderItem.setImage(product.getImage());

        	orderItem.setPurchasePrice(product.getPurchasePrice());
        	orderItem.setSellingPrice(product.getSellingPrice());
        	orderItem.setFinalPrice(product.getFinalPrice());

        	orderItem.setGstPercentage(product.getGstPercentage());
        	orderItem.setGstAmount(product.getGstAmount());

        	orderItem.setProfit(product.getProfit());

        	// =============================

        	orderItem.setQuantity(cart.getQuantity());

        	orderItem.setPrice(product.getFinalPrice());

        	orderItemRepository.save(orderItem);

        	int remainingStock =
        	        product.getQuantity()
        	        - cart.getQuantity();
         

            if (remainingStock < 0) {
                throw new RuntimeException(
                        "Insufficient stock for "
                                + product.getProductName());
            }

            product.setQuantity(remainingStock);

            productRepository.save(product);

            order.getOrderItems().add(orderItem);
        }

        OrderTracking tracking = new OrderTracking();

        tracking.setOrder(order);
        tracking.setStatus(TrackingStatus.ORDER_CONFIRMED);
        tracking.setLocation("Seller Warehouse");
        tracking.setDescription("Your order has been confirmed.");

        orderTrackingRepository.save(tracking);

        order.getTrackingHistory().add(tracking);

        cartRepository.deleteByUserId(user.getId());

        return order;
    }
 
    
    
    @Override
    public List<Order> getOrdersByUser(Long userId) {

        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getOrdersBySeller(Long sellerId) {

        return orderRepository.findBySellerSellerId(sellerId);
    }

    @Override
    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    @Override
    public Order cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        // Update current order status
        order.setStatus(TrackingStatus.CANCELLED);

        // Create tracking history
        OrderTracking tracking = new OrderTracking();

        tracking.setOrder(order);
        tracking.setStatus(TrackingStatus.CANCELLED);
        tracking.setLocation("System");
        tracking.setDescription("Order has been cancelled.");

        orderTrackingRepository.save(tracking);

        order.getTrackingHistory().add(tracking);

        // Restore stock
        List<OrderItem> items = orderItemRepository.findByOrder(order);

        for (OrderItem item : items) {

            Product product = item.getProduct();

            product.setQuantity(
                    product.getQuantity() + item.getQuantity());

            productRepository.save(product);
        }

        return orderRepository.save(order);
    }

    
    private User validateUser(Long userId){

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
    
    
    private Address validateAddress(Long addressId){

        return addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address not found"));
    }
    
    
    
    private List<Cart> loadCart(User user){

        List<Cart> cartItems =
                cartRepository.findByUserId(user.getId());

        if(cartItems.isEmpty()){

            throw new RuntimeException("Cart is empty");

        }

        return cartItems;
    }
    
    
    private Map<Seller, List<Cart>> groupCartBySeller(
            List<Cart> cartItems){

        Map<Seller,List<Cart>> sellerCartMap =
                new HashMap<>();

        for(Cart cart : cartItems){

            Seller seller =
                    cart.getProduct().getSeller();

            sellerCartMap
                    .computeIfAbsent(
                            seller,
                            s -> new ArrayList<>())
                    .add(cart);

        }

        return sellerCartMap;

    }
    
    
    
    
    
    
    
}
    
    
    