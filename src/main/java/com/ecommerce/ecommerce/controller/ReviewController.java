package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.ReviewDTO;
import com.ecommerce.ecommerce.entity.Review;
import com.ecommerce.ecommerce.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review addReview(@RequestBody ReviewDTO dto) {

        return reviewService.addReview(dto);

    }

    @GetMapping("/product/{productId}")
    public List<Review> getProductReviews(
            @PathVariable Long productId) {

        return reviewService.getProductReviews(productId);

    }

    @GetMapping("/user/{userId}")
    public List<Review> getUserReviews(
            @PathVariable Long userId) {

        return reviewService.getUserReviews(userId);

    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(
            @PathVariable Long reviewId) {

        reviewService.deleteReview(reviewId);

        return "Review Deleted Successfully";

    }

}