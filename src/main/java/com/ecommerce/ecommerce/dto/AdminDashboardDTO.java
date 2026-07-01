package com.ecommerce.ecommerce.dto;

public class AdminDashboardDTO {

    private long totalUsers;
    private long totalSellers;
    private long totalProducts;
    private long totalOrders;

    private long pendingSellers;
    private long pendingProducts;

    // Getters & Setters

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalSellers() {
        return totalSellers;
    }

    public void setTotalSellers(long totalSellers) {
        this.totalSellers = totalSellers;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getPendingSellers() {
        return pendingSellers;
    }

    public void setPendingSellers(long pendingSellers) {
        this.pendingSellers = pendingSellers;
    }

    public long getPendingProducts() {
        return pendingProducts;
    }

    public void setPendingProducts(long pendingProducts) {
        this.pendingProducts = pendingProducts;
    }

}