package com.ecommerce.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sellers")

public class Seller {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long sellerId;

	    private String name;
	    private String email;
	    private String mobile;
	    private String alternateMobile;
	    private String password;
	    private String businessName;
	    private String businessType;
	    private String aadharNumber;
	    private String businessLicense;


	    private String shopName;
	    private String shopAddress;
	    private String city;
	    private String state;
	    private String pincode;
	   

	    private String category;
	    private String productType;

	    private String shopLogo;
	    private String shopFrontPhoto;
	    private String shopInsidePhoto;

	    private String gstNumber;
	    private String panNumber;

	    private String accountHolderName;
	    private String accountNumber;
	    private String bankName ;
	    private String ifscCode;
	    private String upiId;
	    
	    private String pickupAddress;
	    private String nomineeName;
	    private String nomineeMobile;
	    
	    private String otp;
	    private  LocalDateTime otpExpiry;

	    private String status = "PENDING";

		public Long getSellerId() {
			return sellerId;
		}

		public void setSellerId(Long sellerId) {
			this.sellerId = sellerId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getShopAddress() {
			return shopAddress;
		}

		public void setShopAddress(String shopAddress) {
			this.shopAddress = shopAddress;
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

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public String getShopLogo() {
			return shopLogo;
		}

		public void setShopLogo(String shopLogo) {
			this.shopLogo = shopLogo;
		}

		public String getShopFrontPhoto() {
			return shopFrontPhoto;
		}

		public void setShopFrontPhoto(String shopFrontPhoto) {
			this.shopFrontPhoto = shopFrontPhoto;
		}

		public String getShopInsidePhoto() {
			return shopInsidePhoto;
		}

		public void setShopInsidePhoto(String shopInsidePhoto) {
			this.shopInsidePhoto = shopInsidePhoto;
		}

		public String getGstNumber() {
			return gstNumber;
		}

		public void setGstNumber(String gstNumber) {
			this.gstNumber = gstNumber;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public String getAccountHolderName() {
			return accountHolderName;
		}

		public void setAccountHolderName(String accountHolderName) {
			this.accountHolderName = accountHolderName;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getUpiId() {
			return upiId;
		}

		public void setUpiId(String upiId) {
			this.upiId = upiId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAlternateMobile() {
			return alternateMobile;
		}

		public void setAlternateMobile(String alternateMobile) {
			this.alternateMobile = alternateMobile;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getBusinessType() {
			return businessType;
		}

		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getPickupAddress() {
			return pickupAddress;
		}

		public void setPickupAddress(String pickupAddress) {
			this.pickupAddress = pickupAddress;
		}

		public String getNomineeName() {
			return nomineeName;
		}

		public void setNomineeName(String nomineeName) {
			this.nomineeName = nomineeName;
		}

		public String getNomineeMobile() {
			return nomineeMobile;
		}

		public void setNomineeMobile(String nomineeMobile) {
			this.nomineeMobile = nomineeMobile;
		}

		public String getAadharNumber() {
			return aadharNumber;
		}

		public void setAadharNumber(String aadharNumber) {
			this.aadharNumber = aadharNumber;
		}

		public String getBusinessLicense() {
			return businessLicense;
		}

		public void setBusinessLicense(String businessLicense) {
			this.businessLicense = businessLicense;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public LocalDateTime getOtpExpiry() {
			return otpExpiry;
		}

		public void setOtpExpiry(LocalDateTime otpExpiry) {
			this.otpExpiry = otpExpiry;
		}

		
        
		

	
	}

