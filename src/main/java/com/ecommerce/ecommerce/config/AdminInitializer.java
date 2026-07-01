package com.ecommerce.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce.entity.Admin;
import com.ecommerce.ecommerce.repository.AdminRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(AdminRepository adminRepository,
                            PasswordEncoder passwordEncoder) {

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // Check whether admin already exists

        if (adminRepository.findByEmail("admin@shopkart.com") == null) {

            Admin admin = new Admin();

            admin.setFirstName("System");
            admin.setLastName("Administrator");

            admin.setCompanyIdNumber("SHOPKART-ADMIN-001");

            admin.setEmail("admin@shopkart.com");

            admin.setMobile("9999999999");

            admin.setPassword(
                    passwordEncoder.encode("admin123")
            );

            adminRepository.save(admin);

            System.out.println(
                    "Default Admin Created Successfully."
            );

        }
        else {

            System.out.println(
                    "Admin Already Exists."
            );

        }

    }

}