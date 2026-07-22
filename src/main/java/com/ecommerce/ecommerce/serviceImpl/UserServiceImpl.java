package com.ecommerce.ecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dto.OtpRequest;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void sendOtp(OtpRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        user.setOtp(String.valueOf(otp));
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        System.out.println("Generated OTP : " + otp);
    }

    @Override
    public boolean verifyOtp(Long userId, String otp) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getOtp() != null
                && user.getOtp().equals(otp)
                && user.getOtpExpiry() != null
                && user.getOtpExpiry().isAfter(LocalDateTime.now());
    }

    @Override
    public String changePassword(Long userId,
                                 String newPassword) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        // Clear OTP after successful password change
        user.setOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);

        return "Password changed successfully";
    }

    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {

        User existing = userRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setFirstName(user.getFirstName());
            existing.setLastName(user.getLastName());
            existing.setEmail(user.getEmail());
            existing.setPassword(user.getPassword());
            existing.setMobile(user.getMobile());
            existing.setAddress(user.getAddress());

            return userRepository.save(existing);
        }

        return null;
    }



    @Override
    @Transactional
    public void deleteUser(Long id) {

        addressRepository.deleteByUser_Id(id);

        userRepository.deleteById(id);

    }

    @Override
    public String forgotPassword(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email not found"));

        String otp = String.valueOf(
                100000 + new Random().nextInt(900000));

        user.setOtp(otp);
        user.setOtpExpiry(
                LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        System.out.println("Forgot Password OTP : " + otp);

        return "OTP Generated Successfully";
    }

    @Override
    public boolean verifyForgotOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (user.getOtp() == null) {
            return false;
        }

        if (!user.getOtp().equals(otp)) {
            return false;
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            return false;
        }

        return true;
    }

    @Override
    public String resetPassword(String email, String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        user.setOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);

        return "Password Reset Successfully";
    }
    
 

  
}