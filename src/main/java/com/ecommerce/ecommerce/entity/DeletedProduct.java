package com.ecommerce.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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

    private String image;

    private String image2;

    private String image3;

    private String image4;
    private Double purchasePrice;

    private Double sellingPrice;

    private Double finalPrice;

    private Integer quantity;

    private LocalDateTime deletedAt;
    
    @Column(length = 1000)
    private String description;

    private Double profit;

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
	
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
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

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public Double getProfit() {
	    return profit;
	}

	public void setProfit(Double profit) {
	    this.profit = profit;
	}
	
	

	

  
    
    
}
