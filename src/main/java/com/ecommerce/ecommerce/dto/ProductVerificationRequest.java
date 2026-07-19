package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ProductVerificationRequest {

	
	   @Min(0)
	    @Max(100)
    private Double adminDiscount;
    private Double discountAmount;
    private Double finalSellingPrice;
    private Double sellerProfit;
    private Double adminContribution;
    private String adminRemark;
 
    
    public Double getAdminDiscount() {
        return adminDiscount;
    }

    public void setAdminDiscount(Double adminDiscount) {
        this.adminDiscount = adminDiscount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getFinalSellingPrice() {
        return finalSellingPrice;
    }

    public void setFinalSellingPrice(Double finalSellingPrice) {
        this.finalSellingPrice = finalSellingPrice;
    }

    public Double getSellerProfit() {
        return sellerProfit;
    }

    public void setSellerProfit(Double sellerProfit) {
        this.sellerProfit = sellerProfit;
    }

    public Double getAdminContribution() {
        return adminContribution;
    }

    public void setAdminContribution(Double adminContribution) {
        this.adminContribution = adminContribution;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }
}