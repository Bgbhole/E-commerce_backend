package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.AdminDashboardDTO;
import com.ecommerce.ecommerce.dto.ProductVerificationRequest;
import com.ecommerce.ecommerce.entity.Admin;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.service.AdminService;



@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private ProductRepository productRepository;
    
    
    @PostMapping("/login")
    public Admin login(

    @RequestBody Admin admin){

    return adminService.login(

    admin.getEmail(),
    admin.getPassword()

    );

    }
    
    @GetMapping("/dashboard")
    public AdminDashboardDTO dashboard() {

        return adminService.getDashboard();

    }
    
 
    
    @GetMapping("/pending-sellers")
    public List<Seller> pendingSellers(){

    return adminService.getPendingSellers();

    }
    
    @PutMapping("/approve-seller/{sellerId}")
    public String approveSeller(
            @PathVariable Long sellerId){

        return adminService.approveSeller(sellerId);

    }
    
    @PutMapping("/reject-seller/{sellerId}")
    public String rejectSeller(
            @PathVariable Long sellerId){

        return adminService.rejectSeller(sellerId);

    }
    
 
    

//======================================
//APPROVE PRODUCT
//======================================

    @PutMapping("/approve-product/{id}")
    public ResponseEntity<String> approveProduct(
            @PathVariable Long id,
            @RequestBody ProductVerificationRequest request) {

        return ResponseEntity.ok(
                adminService.approveProduct(id, request));
    }

//======================================
//REJECT PRODUCT
//======================================

    @PutMapping("/reject-product/{id}")
    public ResponseEntity<String> rejectProduct(
            @PathVariable Long id,
            @RequestBody ProductVerificationRequest request) {

        return ResponseEntity.ok(
                adminService.rejectProduct(id, request));
    }

// ======================================
// GET ALL PENDING PRODUCTS
// ======================================

    @GetMapping("/pending-products")
    public List<Product> getPendingProducts() {

        return adminService.getPendingProducts();

    }


}