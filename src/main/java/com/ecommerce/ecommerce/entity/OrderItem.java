	package com.ecommerce.ecommerce.entity;
	
	import jakarta.persistence.Table;
	
	import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
	
	@Entity
	@Table(name = "order_items")
	public class OrderItem {
		
		 @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long orderItemId;
	
		    @ManyToOne
		    @JoinColumn(name = "order_id")
		    @JsonBackReference("order-items")
		    private Order order;
	
		    @ManyToOne
		    @JoinColumn(name = "product_id")
		    private Product product;
	
		    private Integer quantity;
	
		    private Double price;
		    
		    
		    
		    private String productName;
	
		    private String brand;
	
		    private String category;
	
		    private String image;	
		    private double purchasePrice;
	
		    private double sellingPrice;
	
		    private double finalPrice;
	
		    private double gstPercentage;
	
		    private double gstAmount;
	
		    private double profit;
		    
		    
		    
		    
	
			public String getProductName() {
				return productName;
			}
	
			public void setProductName(String productName) {
				this.productName = productName;
			}
	
			public String getBrand() {
				return brand;
			}
	
			public void setBrand(String brand) {
				this.brand = brand;
			}
	
			public String getCategory() {
				return category;
			}
	
			public void setCategory(String category) {
				this.category = category;
			}
	

			
	
			public String getImage() {
				return image;
			}

			public void setImage(String image) {
				this.image = image;
			}

			public double getPurchasePrice() {
				return purchasePrice;
			}
	
			public void setPurchasePrice(double purchasePrice) {
				this.purchasePrice = purchasePrice;
			}
	
			public double getSellingPrice() {
				return sellingPrice;
			}
	
			public void setSellingPrice(double sellingPrice) {
				this.sellingPrice = sellingPrice;
			}
	
			public double getFinalPrice() {
				return finalPrice;
			}
	
			public void setFinalPrice(double finalPrice) {
				this.finalPrice = finalPrice;
			}
	
			public double getGstPercentage() {
				return gstPercentage;
			}
	
			public void setGstPercentage(double gstPercentage) {
				this.gstPercentage = gstPercentage;
			}
	
			public double getGstAmount() {
				return gstAmount;
			}
	
			public void setGstAmount(double gstAmount) {
				this.gstAmount = gstAmount;
			}
	
			public double getProfit() {
				return profit;
			}
	
			public void setProfit(double profit) {
				this.profit = profit;
			}
	
			public Long getOrderItemId() {
				return orderItemId;
			}
	
			public void setOrderItemId(Long orderItemId) {
				this.orderItemId = orderItemId;
			}
	
			public Order getOrder() {
				return order;
			}
	
			public void setOrder(Order order) {
				this.order = order;
			}
	
			public Product getProduct() {
				return product;
			}
	
			public void setProduct(Product product) {
				this.product = product;
			}
	
			public Integer getQuantity() {
				return quantity;
			}
	
			public void setQuantity(Integer quantity) {
				this.quantity = quantity;
			}
	
			public Double getPrice() {
				return price;
			}
	
			public void setPrice(Double price) {
				this.price = price;
			}
	
		  
		
		    
		    
		    
	}
