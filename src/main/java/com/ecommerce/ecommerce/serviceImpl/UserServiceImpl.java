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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;

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
                                 String oldPassword,
                                 String newPassword) {

        System.out.println("UserId = " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("DB Password = " + user.getPassword());

        if (!user.getPassword().equals(oldPassword)) {

            return "Old password incorrect";

        }

        user.setPassword(newPassword);

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
}