package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.Wishlist;
import com.ecommerce.ecommerce.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Add Product To Wishlist
    @PostMapping("/add/{userId}/{productId}")
    public Wishlist addWishlist(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        return wishlistService.add(userId, productId);

    }

    // Get User Wishlist
    @GetMapping("/user/{userId}")
    public List<Wishlist> getWishlist(
            @PathVariable Long userId) {

        return wishlistService.getUserWishlist(userId);

    }

    // Remove Wishlist Item
    @DeleteMapping("/{wishlistId}")
    public String removeWishlist(
            @PathVariable Long wishlistId) {

        wishlistService.remove(wishlistId);

        return "Wishlist Item Removed Successfully";

    }

}