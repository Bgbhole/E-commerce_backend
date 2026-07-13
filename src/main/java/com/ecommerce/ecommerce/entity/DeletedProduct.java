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

    @Lob
    private byte[] image;
    
    @Lob
    private byte[] image2;

    @Lob
    private byte[] image3;

    @Lob
    private byte[] image4;

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
	
	public byte[] getImage() {
	    return image;
	}

	public void setImage(byte[] image) {
	    this.image = image;
	}

	public byte[] getImage2() {
	    return image2;
	}

	public void setImage2(byte[] image2) {
	    this.image2 = image2;
	}

	public byte[] getImage3() {
	    return image3;
	}

	public void setImage3(byte[] image3) {
	    this.image3 = image3;
	}

	public byte[] getImage4() {
	    return image4;
	}

	public void setImage4(byte[] image4) {
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
		// TODO Auto-generated method stub
		return null;
	}

	public double getProfit() {
		// TODO Auto-generated method stub
		return 0;
	}

	

  
    
    
}
