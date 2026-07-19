package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.AdminDashboardDTO;
import com.ecommerce.ecommerce.dto.ProductVerificationRequest;
import com.ecommerce.ecommerce.entity.Admin;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;

public interface AdminService {

	Admin login(String email, String password);
	
    AdminDashboardDTO getDashboard();
    List<Seller> getPendingSellers();

    String approveSeller(Long sellerId);

    String rejectSeller(Long sellerId);
    List<Product> getPendingProducts();

    String approveProduct(Long productId, ProductVerificationRequest request);

    String rejectProduct(Long productId, ProductVerificationRequest request);
    
    
    
    
}