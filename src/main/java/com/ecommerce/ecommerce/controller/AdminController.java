package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.AdminDashboardDTO;
import com.ecommerce.ecommerce.entity.Admin;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
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
    
    @PutMapping("/approve-product/{productId}")
    public String approveProduct(
            @PathVariable Long productId){

        return adminService.approveProduct(productId);

    }
    
    @PutMapping("/reject-product/{productId}")
    public String rejectProduct(
            @PathVariable Long productId){

        return adminService.rejectProduct(productId);

    }

}