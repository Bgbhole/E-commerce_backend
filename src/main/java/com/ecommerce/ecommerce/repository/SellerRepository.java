package com.ecommerce.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmailAndPassword(String email, String password);

	Optional<Seller> findByEmail(String email);

}