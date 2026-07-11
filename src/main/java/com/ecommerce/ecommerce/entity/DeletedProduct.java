package com.ecommerce.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deleted_products")
public class DeletedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deletedProductId;

    private Long originalProductId;

    private String productName;

    private String brand;

    private String category;

    @Lob
    private byte[] image;

    private Double purchasePrice;

    private Double sellingPrice;

    private Double finalPrice;

    private Integer quantity;

    private LocalDateTime deletedAt;

    @ManyToOne
    private Seller seller;

	public Long getDeletedProductId() {
		return deletedProductId;
	}

	public void setDeletedProductId(Long deletedProductId) {
		this.deletedProductId = deletedProductId;
	}

	public Long getOriginalProductId() {
		return originalProductId;
	}

	public void setOriginalProductId(Long originalProductId) {
		this.originalProductId = originalProductId;
	}

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

    // other fields...
    
    
    
}
