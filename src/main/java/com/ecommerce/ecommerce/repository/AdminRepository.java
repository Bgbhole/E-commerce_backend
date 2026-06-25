package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Admin;

public interface AdminRepository
extends JpaRepository<Admin, Long> {

Admin findByEmail(String email);


}
