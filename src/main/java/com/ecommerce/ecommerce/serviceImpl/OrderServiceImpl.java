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
    @Transactional
    public Order placeOrder(PlaceOrderRequest request) {

        System.out.println("========== PLACE ORDER ==========");
        System.out.println("UserId = " + request.getUserId());
        System.out.println("DeliveryAddressId = " + request.getDeliveryAddressId());
        System.out.println("BillingAddressId = " + request.getBillingAddressId());
        System.out.println("PaymentMethod = " + request.getPaymentMethod());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address deliveryAddress = addressRepository.findById(request.getDeliveryAddressId())
                .orElseThrow(() -> new RuntimeException("Delivery Address not found"));

        Address billingAddress = addressRepository.findById(request.getBillingAddressId())
                .orElse(deliveryAddress);

        List<Cart> cartItems = cartRepository.findByUserId(user.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Seller seller = cartItems.get(0).getProduct().getSeller();

        if (seller == null) {
            throw new RuntimeException("Seller not found");
        }

        Order order = new Order();

        order.setUser(user);

        order.setSeller(seller);

        order.setSellerName(seller.getName());
        order.setSellerEmail(seller.getEmail());
        order.setSellerMobile(seller.getMobile());
        order.setShopName(seller.getShopName());

        order.setOrderDate(LocalDateTime.now());

        order.setStatus(TrackingStatus.ORDER_CONFIRMED);

        order.setPaymentMethod(request.getPaymentMethod());

        if ("COD".equalsIgnoreCase(request.getPaymentMethod())) {
            order.setPaymentStatus("PENDING");
        } else {
            order.setPaymentStatus("SUCCESS");
        }

        order.setTrackingNumber(System.currentTimeMillis());

        // Delivery Address
        order.setDeliveryName(deliveryAddress.getFullName());
        order.setDeliveryMobile(deliveryAddress.getMobile());
        order.setDeliveryAddress(deliveryAddress.getFullAddress());
        order.setDeliveryCity(deliveryAddress.getCity());
        order.setDeliveryState(deliveryAddress.getState());
        order.setDeliveryPincode(deliveryAddress.getPincode());

        double total = 0;

        for (Cart cart : cartItems) {
            total += cart.getProduct().getFinalPrice() * cart.getQuantity();
        }

        order.setTotalAmount(total);

        order = orderRepository.save(order);

        for (Cart cart : cartItems) {

            Product product = cart.getProduct();

            if (product.getQuantity() < cart.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for " + product.getProductName());
            }

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(product);

            item.setProductName(product.getProductName());
            item.setBrand(product.getBrand());
            item.setCategory(product.getCategory());
            item.setImage(product.getImage());

            item.setPurchasePrice(product.getPurchasePrice());
            item.setSellingPrice(product.getSellingPrice());
            item.setFinalPrice(product.getFinalPrice());

            item.setGstPercentage(product.getGstPercentage());
            item.setGstAmount(product.getGstAmount());

            item.setProfit(product.getProfit());

            item.setQuantity(cart.getQuantity());
            item.setPrice(product.getFinalPrice());

            orderItemRepository.save(item);

            product.setQuantity(product.getQuantity() - cart.getQuantity());

            productRepository.save(product);

            order.getOrderItems().add(item);
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

  



    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();

    }
    
    @Override
    @Transactional
    public Order updateOrder(Long orderId, Order request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order Not Found"));

        // Update Order
        order.setDeliveryName(request.getDeliveryName());
        order.setDeliveryMobile(request.getDeliveryMobile());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setDeliveryCity(request.getDeliveryCity());
        order.setDeliveryState(request.getDeliveryState());
        order.setDeliveryPincode(request.getDeliveryPincode());

        orderRepository.save(order);

        // Update Customer Address
        User user = order.getUser();

        List<Address> addresses = addressRepository.findByUserId(user.getId());

        if (!addresses.isEmpty()) {

            Address address = addresses.get(0);

            address.setFullName(request.getDeliveryName());
            address.setMobile(request.getDeliveryMobile());
            address.setFullAddress(request.getDeliveryAddress());
            address.setCity(request.getDeliveryCity());
            address.setState(request.getDeliveryState());
            address.setPincode(request.getDeliveryPincode());

            addressRepository.save(address);
        }

        return order;
    }
    
    
    
    
}
    
    
    