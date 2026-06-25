package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.RegisterRequest;
import com.ecommerce.ecommerce.entity.User;

public interface AuthService {

    User register(RegisterRequest request);

    User login(LoginRequest request);
}