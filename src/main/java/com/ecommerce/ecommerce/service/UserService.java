package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.OtpRequest;
import com.ecommerce.ecommerce.entity.User;


public interface UserService {

    void sendOtp(OtpRequest request);

    boolean verifyOtp(Long userId, String otp);

    String changePassword(Long userId,
                          String oldPassword,
                          String newPassword);

	List<User> getAllUsers();

	User getUserById(Long id);

	User updateUser(Long id, User user);

	void deleteUser(Long id);

}