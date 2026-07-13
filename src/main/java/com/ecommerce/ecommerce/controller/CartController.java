package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.CartRequest;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add To Cart
    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartRequest request) {

        return cartService.addToCart(request);

    }

    // Get User Cart
    @GetMapping("/user/{userId}")
    public List<Cart> getUserCart(
            @PathVariable Long userId) {

        return cartService.getUserCart(userId);

    }

    // Update Quantity
    @PutMapping("/quantity/{cartId}")
    public Cart updateQuantity(

            @PathVariable Long cartId,

            @RequestParam int quantity) {

        return cartService.updateQuantity(cartId, quantity);

    }

    // Remove Item
    @DeleteMapping("/{cartId}")
    public String removeCartItem(
            @PathVariable Long cartId) {

        cartService.deleteCartItem(cartId);

        return "Cart Item Removed Successfully";

    }

    // Clear Cart
    @DeleteMapping("/clear/{userId}")
    public String clearCart(
            @PathVariable Long userId) {

        cartService.clearCart(userId);

        return "Cart Cleared Successfully";

    }

}