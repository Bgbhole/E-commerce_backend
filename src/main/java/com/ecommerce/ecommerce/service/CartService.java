package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.CartRequest;
import com.ecommerce.ecommerce.entity.Cart;

public interface CartService {

    Cart addToCart(CartRequest request);

    List<Cart> getUserCart(Long userId);

    Cart updateQuantity(Long cartId, Integer quantity);

    void deleteCartItem(Long cartId);

    void clearCart(Long userId);

}