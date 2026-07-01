package com.ecommerce.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.enums.SellerStatus;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmailAndPassword(String email, String password);

	Optional<Seller> findByEmail(String email);
	List<Seller> findByStatus(SellerStatus status);
	long countByStatus(SellerStatus status);
	
}