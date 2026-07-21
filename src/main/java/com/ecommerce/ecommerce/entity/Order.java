package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.enums.TrackingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private TrackingStatus status;

    private String paymentMethod;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({
        "password",
        "otp",
        "otpExpiry"
    })
    
    private User user;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    
    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference("tracking")
    private List<OrderTracking> trackingHistory = new ArrayList<>();
    
    
   
    
    
    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference("order-items")	
 private List<OrderItem> orderItems = new ArrayList<>();
    
    
    private String deliveryName;

    private String deliveryMobile;

    private String deliveryAddress;

    private String deliveryCity;

    private String deliveryState;

    private String deliveryPincode;
    
    
    
    private String paymentStatus;

    private Long trackingNumber;
    
    private String sellerName;

    private String sellerEmail;

    private String sellerMobile;

    private String shopName;
    
    private Double sellerAmount;

    private Double adminCommission;

    private String sellerPaymentStatus;

    private LocalDateTime sellerPaymentDate;

    private String sellerTransactionId;
    
    private String sellerPaymentMethod;
    
    private Double sellerPrice;              // Original seller price
    private Double customerPaidAmount;       // Amount paid by customer
    private Double shopkartContribution;     // Discount funded by ShopKart

   
    private Double platformFeePercentage;

    private Double platformFeeAmount;

    private Double sellerNetProfit;
    // Getters and Setters
    
    
    

    public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(Long trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryMobile() {
		return deliveryMobile;
	}

	public void setDeliveryMobile(String deliveryMobile) {
		this.deliveryMobile = deliveryMobile;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryPincode() {
		return deliveryPincode;
	}

	public void setDeliveryPincode(String deliveryPincode) {
		this.deliveryPincode = deliveryPincode;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public List<OrderTracking> getTrackingHistory() {
		return trackingHistory;
	}

	public void setTrackingHistory(List<OrderTracking> trackingHistory) {
		this.trackingHistory = trackingHistory;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public TrackingStatus getStatus() {
        return status;
    }

    public void setStatus(TrackingStatus status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    
    public Double getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(Double sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    public Double getAdminCommission() {
        return adminCommission;
    }

    public void setAdminCommission(Double adminCommission) {
        this.adminCommission = adminCommission;
    }

    public String getSellerPaymentStatus() {
        return sellerPaymentStatus;
    }

    public void setSellerPaymentStatus(String sellerPaymentStatus) {
        this.sellerPaymentStatus = sellerPaymentStatus;
    }

    public LocalDateTime getSellerPaymentDate() {
        return sellerPaymentDate;
    }

    public void setSellerPaymentDate(LocalDateTime sellerPaymentDate) {
        this.sellerPaymentDate = sellerPaymentDate;
    }

    public String getSellerTransactionId() {
        return sellerTransactionId;
    }

    public void setSellerTransactionId(String sellerTransactionId) {
        this.sellerTransactionId = sellerTransactionId;
    }

    public String getSellerPaymentMethod() {
        return sellerPaymentMethod;
    }

    public void setSellerPaymentMethod(String sellerPaymentMethod) {
        this.sellerPaymentMethod = sellerPaymentMethod;
    }
    
    public Double getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(Double sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public Double getCustomerPaidAmount() {
        return customerPaidAmount;
    }

    public void setCustomerPaidAmount(Double customerPaidAmount) {
        this.customerPaidAmount = customerPaidAmount;
    }

    public Double getShopkartContribution() {
        return shopkartContribution;
    }

    public void setShopkartContribution(Double shopkartContribution) {
        this.shopkartContribution = shopkartContribution;
    }

    public Double getPlatformFeePercentage() {
        return platformFeePercentage;
    }

    public void setPlatformFeePercentage(Double platformFeePercentage) {
        this.platformFeePercentage = platformFeePercentage;
    }

    public Double getPlatformFeeAmount() {
        return platformFeeAmount;
    }

    public void setPlatformFeeAmount(Double platformFeeAmount) {
        this.platformFeeAmount = platformFeeAmount;
    }

    public Double getSellerNetProfit() {
        return sellerNetProfit;
    }

    public void setSellerNetProfit(Double sellerNetProfit) {
        this.sellerNetProfit = sellerNetProfit;
    }

}