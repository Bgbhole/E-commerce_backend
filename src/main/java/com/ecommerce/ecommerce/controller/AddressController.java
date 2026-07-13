package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.Address;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.UserRepository;


@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

    // ================= ADD ADDRESS =================

	@PostMapping("/add/{userId}")
	public Address addAddress(
	        @PathVariable Long userId,
	        @RequestBody Address address) {

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    address.setUser(user);

	    return addressRepository.save(address);
	}

    // ================= GET USER ADDRESSES =================

	@GetMapping("/{userId}")
	public List<Address> getAddresses(
	        @PathVariable Long userId) {

	    return addressRepository.findByUserId(userId);
	}

    // ================= UPDATE ADDRESS =================

	@PutMapping("/update")
	public Address updateAddress(
	        @RequestBody Address address) {

	    Address oldAddress = addressRepository.findById(address.getAddressId())
	            .orElseThrow(() -> new RuntimeException("Address Not Found"));

	    oldAddress.setFullName(address.getFullName());
	    oldAddress.setMobile(address.getMobile());
	    oldAddress.setFullAddress(address.getFullAddress());
	    oldAddress.setCity(address.getCity());
	    oldAddress.setState(address.getState());
	    oldAddress.setPincode(address.getPincode());

	    return addressRepository.save(oldAddress);
	}

    // ================= DELETE ADDRESS =================

	@DeleteMapping("/{addressId}")
	public String deleteAddress(
	        @PathVariable Long addressId) {

	    addressRepository.deleteById(addressId);

	    return "Address Deleted Successfully";
	}

}