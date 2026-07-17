package com.ecommerce.ecommerce.dto;

public class UpdateDeliveryRequest {

    private String deliveryName;
    private String deliveryMobile;
    private String deliveryAddress;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryPincode;

    public UpdateDeliveryRequest() {
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryPincode() {
        return deliveryPincode;
    }

    public void setDeliveryPincode(String deliveryPincode) {
        this.deliveryPincode = deliveryPincode;
    }

}