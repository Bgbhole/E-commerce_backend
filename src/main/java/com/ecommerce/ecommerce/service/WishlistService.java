package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.entity.Wishlist;

public interface WishlistService {

    Wishlist add(Long userId, Long productId);

    List<Wishlist> getUserWishlist(Long userId);

    void remove(Long wishlistId);

}