package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.CartRequest;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add Product To Cart
    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartRequest request) {

        return cartService.addToCart(request);

    }

    // Get User Cart
    @GetMapping("/user/{userId}")
    public List<Cart> getUserCart(@PathVariable Long userId) {

        return cartService.getUserCart(userId);

    }

    // Update Quantity
  
    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable Long cartId) {

        cartService.deleteCartItem(cartId);

        return "Deleted Successfully";
    }
    
    
    // Clear Complete Cart
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Long userId) {

        cartService.clearCart(userId);

        return "Cart cleared successfully.";

    }
    
    
    @PutMapping("/quantity/{cartId}")
    public Cart updateQuantity(@PathVariable Long cartId,
                               @RequestParam Integer quantity) {

        return cartService.updateQuantity(cartId, quantity);

    }
    

}