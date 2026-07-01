
package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.SellerChangePasswordDTO;
import com.ecommerce.ecommerce.dto.VerifyOtpRequest;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.service.SellerService;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin("*")
public class SellerController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    // Register Seller
    @PostMapping("/register")
    public Seller registerSeller(@RequestBody Seller seller) {

        return sellerService.registerSeller(seller);

    }

    // Seller Login
    @PostMapping("/login")
    public Seller loginSeller(@RequestBody Seller seller) {

        return sellerService.loginSeller(
                seller.getEmail(),
                seller.getPassword());

    }

    // Get Seller By Id
    @GetMapping("/{sellerId}")
    public Seller getSellerById(@PathVariable Long sellerId) {

        return sellerRepository.findById(sellerId).orElse(null);

    }

    // Get All Sellers
    @GetMapping
    public List<Seller> getAllSellers() {

        return sellerRepository.findAll();

    }

    // Update Seller
    @PutMapping("/update")
    public Seller updateSeller(
            @RequestBody Seller updatedSeller) {

        Seller seller =
                sellerRepository
                        .findById(updatedSeller.getSellerId())
                        .orElse(null);

        if (seller != null) {

            seller.setName(updatedSeller.getName());
            seller.setEmail(updatedSeller.getEmail());
            seller.setMobile(updatedSeller.getMobile());

            seller.setShopName(updatedSeller.getShopName());
            seller.setShopAddress(updatedSeller.getShopAddress());

            seller.setCity(updatedSeller.getCity());
            seller.setState(updatedSeller.getState());
            seller.setPincode(updatedSeller.getPincode());
            
            seller.setBusinessName(updatedSeller.getBusinessName());
            seller.setCategory(updatedSeller.getCategory());
            seller.setProductType(updatedSeller.getProductType());

            seller.setGstNumber(updatedSeller.getGstNumber());
            seller.setPanNumber(updatedSeller.getPanNumber());

            seller.setAccountHolderName(
                    updatedSeller.getAccountHolderName());

            seller.setBankName(
                    updatedSeller.getBankName());

            seller.setAccountNumber(
                    updatedSeller.getAccountNumber());

            seller.setIfscCode(
                    updatedSeller.getIfscCode());

            seller.setUpiId(
                    updatedSeller.getUpiId());

            seller.setPickupAddress(
                    updatedSeller.getPickupAddress());

            seller.setAlternateMobile(
                    updatedSeller.getAlternateMobile());

            seller.setBusinessLicense(
                    updatedSeller.getBusinessLicense());

            seller.setAadharNumber(
                    updatedSeller.getAadharNumber());

            seller.setNomineeName(
                    updatedSeller.getNomineeName());

            seller.setNomineeMobile(
                    updatedSeller.getNomineeMobile());

            return sellerRepository.save(seller);

        }

        return null;

    }

    // Delete Seller
    @DeleteMapping("/{sellerId}")
    public String deleteSeller(
            @PathVariable Long sellerId) {

        sellerRepository.deleteById(sellerId);

        return "Seller Deleted Successfully";

    
}
    
    @PostMapping("/send-edit-otp/{sellerId}")
    public String sendEditOtp(@PathVariable Long sellerId) {

        Seller seller = sellerRepository
                .findById(sellerId)
                .orElseThrow();

        String otp =
                String.valueOf((int)(100000 + Math.random() * 900000));

        seller.setOtp(otp);

        sellerRepository.save(seller);

        // send email here

        return "OTP Sent";
    }
    
    @PostMapping("/verify-edit-otp")
    public String verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        Seller seller =
                sellerRepository
                        .findById(request.getUserId())
                        .orElseThrow();

        if (seller.getOtp().equals(request.getOtp())) {
            return "OTP Verified";
        }

        return "Invalid OTP";
    }
    
    @PostMapping("/change-password")
    public String changePassword(
            @RequestBody SellerChangePasswordDTO request) {

        Seller seller =
                sellerRepository
                        .findById(request.getSellerId())
                        .orElseThrow();

        System.out.println("Seller ID = " + request.getSellerId());
        System.out.println("Entered OTP = " + request.getOtp());
        System.out.println("DB OTP = " + seller.getOtp());

        if (!seller.getOtp().trim()
                .equals(request.getOtp().trim())) {

            return "Invalid OTP";
        }

        seller.setPassword(
        	    passwordEncoder.encode(request.getNewPassword())
        	);

        seller.setOtp(null);

        sellerRepository.save(seller);

        return "Password Changed Successfully";
    }
    
  

    
}
