package com.ecommerce.ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.RegisterRequest;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;
    
    @Override
    public User register(RegisterRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword( passwordEncoder.encode(request.getPassword()) );
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest request) {

    	User user = userRepository .findByEmail(request.getEmail()) .orElseThrow(() -> new RuntimeException("Invalid Email"));
    	if (!passwordEncoder.matches( request.getPassword(), user.getPassword())) { throw new RuntimeException("Invalid Password"); }
        return user;
    }
}