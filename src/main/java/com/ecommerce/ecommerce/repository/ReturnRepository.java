package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.ReturnRequest;

public interface ReturnRepository
        extends JpaRepository<ReturnRequest, Long> {

    List<ReturnRequest> findByUserId(Long userId);

    List<ReturnRequest> findBySellerSellerId(Long sellerId);

}