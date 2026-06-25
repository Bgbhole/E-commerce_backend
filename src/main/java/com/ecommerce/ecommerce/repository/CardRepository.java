package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Long>{

    List<Card> findByCustomerId(Long customerId);

}
