package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

    List<Review> findByProductProductId(Long productId);

    List<Review> findByUserId(Long userId);

}