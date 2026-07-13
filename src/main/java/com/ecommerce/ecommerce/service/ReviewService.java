package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.ReviewDTO;
import com.ecommerce.ecommerce.entity.Review;

public interface ReviewService {

    Review addReview(ReviewDTO dto);

    List<Review> getProductReviews(Long productId);

    List<Review> getUserReviews(Long userId);

    void deleteReview(Long reviewId);

	

}
