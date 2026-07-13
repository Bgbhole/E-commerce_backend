package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.CartRequest;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addToCart(CartRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository
                .findByUserIdAndProductProductId(
                        request.getUserId(),
                        request.getProductId())
                .orElse(null);
        
        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Product is out of stock");
        }

        if (cart != null) {

            cart.setQuantity(cart.getQuantity() + request.getQuantity());

        } else {

            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(request.getQuantity());

        }

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getUserCart(Long userId) {

        return cartRepository.findByUserId(userId);

    }

    @Override
    public Cart updateQuantity(Long cartId, Integer quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity < 1) {
            quantity = 1;
        }

        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCartItem(Long cartId) {

        cartRepository.deleteById(cartId);

    }

    @Override
    public void clearCart(Long userId) {

        cartRepository.deleteByUserId(userId);

    }

}