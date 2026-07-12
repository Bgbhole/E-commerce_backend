package com.ecommerce.ecommerce.dto;

public class ProductDTO {

	private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private int stock;
    private String category;
    private Long sellerId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
	}
    
    
}
