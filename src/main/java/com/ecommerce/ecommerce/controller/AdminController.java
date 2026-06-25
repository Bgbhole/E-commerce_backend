package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.Admin;
import com.ecommerce.ecommerce.repository.AdminRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

@Autowired
private AdminRepository adminRepository;

@PostMapping("/register")
public Admin registerAdmin(
        @RequestBody Admin admin) {

    return adminRepository.save(admin);
}

@PostMapping("/login")
public Admin loginAdmin(
        @RequestBody Admin admin) {

    Admin existingAdmin =
            adminRepository.findByEmail(
                    admin.getEmail());

    if (existingAdmin != null &&
            existingAdmin.getPassword()
                    .equals(admin.getPassword())) {

        return existingAdmin;
    }

    return null;
}


}
