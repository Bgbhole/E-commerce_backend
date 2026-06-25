package com.ecommerce.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "upi_accounts")
public class Upi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upiId;

    private Long customerId;

    private String upiNumber;

	public Long getUpiId() {
		return upiId;
	}

	public void setUpiId(Long upiId) {
		this.upiId = upiId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUpiNumber() {
		return upiNumber;
	}

	public void setUpiNumber(String upiNumber) {
		this.upiNumber = upiNumber;
	}

  

}