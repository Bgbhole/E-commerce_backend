package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.enums.SellerStatus;

public interface ProductRepository extends JpaRepository<Product,Long>{

	List<Product> findBySellerSellerId(Long sellerId);
	List<Product> findByStatusAndSellerStatus(
	        ProductStatus productStatus,
	        SellerStatus sellerStatus);
	List<Product> findByStatus(ProductStatus status);
	long countByStatus(ProductStatus status);
	
}
