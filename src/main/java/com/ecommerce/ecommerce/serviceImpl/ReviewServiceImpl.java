package com.ecommerce.ecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.ReviewDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Review;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.ReviewRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Review addReview(ReviewDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                new RuntimeException("User Not Found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                new RuntimeException("Product Not Found"));

        Review review = new Review();

        review.setUser(user);
        review.setProduct(product);
        review.setRating(dto.getRating());
        review.setReview(dto.getReview());
        review.setReviewDate(LocalDateTime.now());

   

        Review savedReview = reviewRepository.save(review);
        
        List<Review> reviews =
                reviewRepository.findByProductProductId(product.getProductId());

        double average = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);

        Product updatedProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        updatedProduct.setAverageRating(average);
        updatedProduct.setTotalReviews(reviews.size());

        productRepository.save(updatedProduct);
        return savedReview;

    }

    public List<Review> getProductReviews(Long productId) {

        return reviewRepository.findByProductProductId(productId);

    }

    public List<Review> getUserReviews(Long userId) {

        return reviewRepository.findByUserId(userId);

    }

    public void deleteReview(Long reviewId) {

        reviewRepository.deleteById(reviewId);

    }
    
    

}