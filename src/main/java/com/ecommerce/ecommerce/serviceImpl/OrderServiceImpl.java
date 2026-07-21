package com.ecommerce.ecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.PlaceOrderRequest;
import com.ecommerce.ecommerce.dto.SellerPaymentRequest;
import com.ecommerce.ecommerce.dto.UpdateDeliveryRequest;
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

		Address billingAddress = addressRepository.findById(request.getBillingAddressId()).orElse(deliveryAddress);

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

		String payment = request.getPaymentMethod();

		if (payment != null && (payment.equalsIgnoreCase("COD") || payment.equalsIgnoreCase("Cash On Delivery"))) {

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

		double sellerPrice = 0;
		double customerPaid = 0;
		double shopkartContribution = 0;

		double totalPlatformFee = 0;
		double totalSellerNetProfit = 0;

		Double platformFeePercentage = 0.0;

		for (Cart cart : cartItems) {

			Product product = cart.getProduct();

			double price = product.getFinalSellingPrice() != null ? product.getFinalSellingPrice()
					: product.getFinalPrice();

			total += price * cart.getQuantity();
			
			platformFeePercentage = product.getPlatformFeePercentage();

			totalPlatformFee +=
			        product.getPlatformFeeAmount() * cart.getQuantity();

			totalSellerNetProfit +=
			        product.getSellerNetProfit() * cart.getQuantity();
		}

		
		order.setSellerPrice(sellerPrice);

		order.setCustomerPaidAmount(customerPaid);

		order.setShopkartContribution(shopkartContribution);

		

		order.setSellerPaymentStatus("PENDING");
		order.setTotalAmount(total);

		order = orderRepository.save(order);

		for (Cart cart : cartItems) {

			Product product = productRepository.findById(cart.getProduct().getProductId())
					.orElseThrow(() -> new RuntimeException("Product not found"));

			System.out.println("================================");
			System.out.println("Product : " + product.getProductName());
			System.out.println("DB Stock : " + product.getQuantity());
			System.out.println("Cart Qty : " + cart.getQuantity());
			System.out.println("================================");

			if (product.getQuantity() < cart.getQuantity()) {
				throw new RuntimeException("Insufficient stock for " + product.getProductName());
			}

			if (product.getQuantity() < cart.getQuantity()) {
				throw new RuntimeException("Insufficient stock for " + product.getProductName());
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

			item.setGstPercentage(product.getGstPercentage());
			item.setGstAmount(product.getGstAmount());

			item.setProfit(product.getProfit());

			item.setQuantity(cart.getQuantity());

			double finalprice = product.getFinalSellingPrice() != null ? product.getFinalSellingPrice()
					: product.getFinalPrice();

			double originalSellerPrice = product.getSellerPrice() * cart.getQuantity();

			double customerAmount = finalprice * cart.getQuantity();

			double contribution = originalSellerPrice - customerAmount;

			sellerPrice += originalSellerPrice;
			customerPaid += customerAmount;
			shopkartContribution += contribution;

			item.setFinalPrice(finalprice);

			item.setPrice(finalprice);

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

		order.setSellerPrice(sellerPrice);

		order.setCustomerPaidAmount(customerPaid);

		order.setShopkartContribution(shopkartContribution);

		order.setPlatformFeePercentage(platformFeePercentage);

		order.setPlatformFeeAmount(totalPlatformFee);

		order.setSellerNetProfit(totalSellerNetProfit);

		order.setSellerPaymentStatus("PENDING");

		orderRepository.save(order);

		orderRepository.save(order);

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

		return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
	}

	@Override
	public Order cancelOrder(Long orderId) {

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

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

			product.setQuantity(product.getQuantity() + item.getQuantity());

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

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));

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

	@Override
	public Order updateDeliveryAddress(

			Long orderId,

			UpdateDeliveryRequest request) {

		Order order = orderRepository.findById(orderId).orElseThrow();

		if (order.getStatus() != TrackingStatus.ORDER_CONFIRMED) {

			throw new RuntimeException("Address can only be changed before packing.");

		}

		order.setDeliveryName(request.getDeliveryName());
		order.setDeliveryMobile(request.getDeliveryMobile());
		order.setDeliveryAddress(request.getDeliveryAddress());
		order.setDeliveryCity(request.getDeliveryCity());
		order.setDeliveryState(request.getDeliveryState());
		order.setDeliveryPincode(request.getDeliveryPincode());

		orderRepository.save(order);

		// Update customer's default address
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

	@Override
	public Order paySeller(Long orderId, SellerPaymentRequest request) {

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		if (!"SUCCESS".equals(order.getPaymentStatus())) {

			throw new RuntimeException("Customer payment is not completed.");

		}

		if ("PAID".equals(order.getSellerPaymentStatus())) {

			throw new RuntimeException("Seller has already been paid.");

		}

		
		if (order.getStatus() == TrackingStatus.CANCELLED) {

		    throw new RuntimeException(
		            "Cancelled order cannot be paid.");

		}

		if (order.getShopkartContribution() == null
		        || order.getShopkartContribution() <= 0) {

		    throw new RuntimeException(
		            "No ShopKart contribution to pay.");

		}
		
		order.setSellerPaymentStatus("PAID");

		order.setSellerPaymentMethod(request.getPaymentMethod());

		order.setSellerTransactionId(request.getTransactionId());

		order.setSellerPaymentDate(LocalDateTime.now());

		return orderRepository.save(order);
	}
}
