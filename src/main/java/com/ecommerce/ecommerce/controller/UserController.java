package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.OtpRequest;
import com.ecommerce.ecommerce.dto.VerifyOtpRequest;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }
    
    @GetMapping("/{userId}")
    public User getUserById(
            @PathVariable Long userId) {

        return userService.getUserById(userId);

    }
    
    @PutMapping("/update/{userId}")
    public User updateUser(
            @PathVariable Long userId,
            @RequestBody User user) {

        return userService.updateUser(userId, user);

    }
    
   
    
    @DeleteMapping("/{userId}")
    public String deleteUser(
            @PathVariable Long userId) {

        userService.deleteUser(userId);

        return "User Deleted Successfully";

    }
    
    @PostMapping("/send-otp")
    public String sendOtp(
            @RequestBody OtpRequest request) {

        userService.sendOtp(request);

        return "OTP Sent Successfully";

    }
    
    @PostMapping("/verify-otp")
    public boolean verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        return userService.verifyOtp(
                request.getUserId(),
                request.getOtp());
    }
    
    @PostMapping("/change-password")
    public String changePassword(

            @RequestParam Long userId,

            @RequestParam String newPassword) {

        return userService.changePassword(userId, newPassword);

    }
    
    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestParam String email){

        return userService.forgotPassword(email);

    }
    
    @PostMapping("/verify-forgot-otp")
    public boolean verifyForgotOtp(
            @RequestParam String email,
            @RequestParam String otp){

        return userService.verifyForgotOtp(email, otp);
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String email,
            @RequestParam String newPassword){

        return userService.resetPassword(email, newPassword);
    }
    
}