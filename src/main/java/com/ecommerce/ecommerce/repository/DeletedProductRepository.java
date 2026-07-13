package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entity.DeletedProduct;

@Repository
public interface DeletedProductRepository
        extends JpaRepository<DeletedProduct, Long> {

	List<DeletedProduct> findBySellerSellerId(Long sellerId);

}