package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.Wishlist;
import com.ecommerce.ecommerce.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin("*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Add Product
    @PostMapping("/add")
    public Wishlist addProduct(
            @RequestParam Long userId,
            @RequestParam Long productId) {

        return wishlistService.add(userId, productId);

    }

    // User Wishlist
    @GetMapping("/user/{userId}")
    public List<Wishlist> getWishlist(
            @PathVariable Long userId) {

        return wishlistService.getUserWishlist(userId);

    }

    // Remove Product
    @DeleteMapping("/{wishlistId}")
    public String removeProduct(
            @PathVariable Long wishlistId) {

        wishlistService.remove(wishlistId);

        return "Removed Successfully";

    }

}