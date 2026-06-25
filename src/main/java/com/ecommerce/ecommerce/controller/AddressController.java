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
@CrossOrigin("*")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add/{userId}")
    public Address addAddress(@PathVariable Long userId,
                              @RequestBody Address address) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        address.setUser(user);

        return addressRepository.save(address);
    }

    @GetMapping("/{userId}")
    public List<Address> getAddresses(@PathVariable Long userId) {

        return addressRepository.findByUserId(userId);
    }

    @DeleteMapping("/{addressId}")
    public String deleteAddress(@PathVariable Long addressId) {

        addressRepository.deleteById(addressId);

        return "Deleted Successfully";
    }
    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address){

        Address existing = addressRepository.findById(address.getAddressId())
                .orElseThrow();

        existing.setFullName(address.getFullName());
        existing.setMobile(address.getMobile());
        existing.setFullAddress(address.getFullAddress());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setPincode(address.getPincode());

        return addressRepository.save(existing);
    }
    
    @GetMapping("/find/{id}")
    public Address getAddress(@PathVariable Long id){
        return addressRepository.findById(id).orElseThrow();
    }

}