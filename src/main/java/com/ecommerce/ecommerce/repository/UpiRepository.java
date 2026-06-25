package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entity.Upi;

@Repository
public interface UpiRepository extends JpaRepository<Upi,Long>{

    List<Upi> findByCustomerId(Long customerId);

}
