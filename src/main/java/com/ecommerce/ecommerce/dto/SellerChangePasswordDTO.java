package com.ecommerce.ecommerce.dto;


	public class SellerChangePasswordDTO {


	private Long sellerId;

	private String otp;

	private String newPassword;

	public Long getSellerId() {
	    return sellerId;
	}

	public void setSellerId(Long sellerId) {
	    this.sellerId = sellerId;
	}

	public String getOtp() {
	    return otp;
	}

	public void setOtp(String otp) {
	    this.otp = otp;
	}

	public String getNewPassword() {
	    return newPassword;
	}

	public void setNewPassword(String newPassword) {
	    this.newPassword = newPassword;
	}

	}



