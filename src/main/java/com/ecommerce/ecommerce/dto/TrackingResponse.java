package com.ecommerce.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.ecommerce.enums.TrackingStatus;

public class TrackingResponse {

    // Order Details
    private Long orderId;
    private LocalDateTime orderDate;
    private TrackingStatus currentStatus;

    // Product Details
    private Long productId;
    private String productName;
    private String productImage;
    private Integer quantity;
    private BigDecimal price;

    // Seller
    private String sellerName;

    // Delivery Address
    private String customerName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String pincode;

    // Delivery
    private LocalDate estimatedDeliveryDate;

    // Timeline
    private List<TrackingTimelineDTO> timeline;

    public TrackingResponse() {
    }

    // Getters & Setters

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

    public TrackingStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(TrackingStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public List<TrackingTimelineDTO> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TrackingTimelineDTO> timeline) {
        this.timeline = timeline;
    }
}
