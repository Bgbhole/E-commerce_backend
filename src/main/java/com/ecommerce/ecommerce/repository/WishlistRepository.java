package com.ecommerce.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Wishlist;

public interface WishlistRepository
        extends JpaRepository<Wishlist,Long>{

    List<Wishlist> findByUserId(Long userId);

    Optional<Wishlist> findByUserIdAndProductProductId(
            Long userId,
            Long productId);

}