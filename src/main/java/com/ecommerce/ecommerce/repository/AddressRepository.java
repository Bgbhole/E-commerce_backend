package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Address a WHERE a.user.id = :userId")
	void deleteAddressesByUserId(@Param("userId") Long userId);

	List<Address> findByUserId(Long userId);

	void deleteByUser_Id(Long id);
	

}