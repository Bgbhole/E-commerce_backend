package com.ecommerce.ecommerce.service;

import java.util.List;
import com.ecommerce.ecommerce.entity.Seller;

public interface SellerService {

    Seller registerSeller(Seller seller);

    Seller loginSeller(String email, String password);

    Seller getSellerById(Long sellerId);

    List<Seller> getAllSellers();

    Seller updateSeller(Long sellerId, Seller seller);

    void deleteSeller(Long sellerId);

	String forgotPassword(String email);

	boolean verifyForgotOtp(String email, String otp);

	String resetPassword(String email, String newPassword);
}