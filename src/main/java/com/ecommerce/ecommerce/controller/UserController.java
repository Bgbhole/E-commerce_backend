package com.ecommerce.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.dto.OtpRequest;
import com.ecommerce.ecommerce.dto.PasswordRequestDTO;
import com.ecommerce.ecommerce.dto.VerifyOtpRequest;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    // Send OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody OtpRequest request) {

        userService.sendOtp(request);

        return "OTP Sent Successfully";
    }


    // Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOtp() != null
                && user.getOtp().equals(request.getOtp())
                && user.getOtpExpiry() != null
                && user.getOtpExpiry().isAfter(LocalDateTime.now())) {

            return ResponseEntity.ok("Verified");
        }

        return ResponseEntity.badRequest().body("Invalid OTP");
    }


    // Update Profile
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {

        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setGender(updatedUser.getGender());
        user.setAddress(updatedUser.getAddress());
        user.setEmail(updatedUser.getEmail());
        user.setMobile(updatedUser.getMobile());

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }


    @PutMapping("/change-password")
    public String changePassword(
            @RequestBody PasswordRequestDTO request){

        return userService.changePassword(
                request.getUserId(),
                request.getNewPassword());

    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        try {

            userService.deleteUser(id);

            return ResponseEntity.ok("User Deleted Successfully");

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}