package com.ecommerce.ecommerce.dto;

public class ProductDTO {

	private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private int stock;
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
	public Object getImageUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
