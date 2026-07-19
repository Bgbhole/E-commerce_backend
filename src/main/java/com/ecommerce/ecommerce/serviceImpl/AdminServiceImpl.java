package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.AdminDashboardDTO;
import com.ecommerce.ecommerce.entity.Admin;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.enums.SellerStatus;
import com.ecommerce.ecommerce.repository.AdminRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.AdminService;
import com.ecommerce.ecommerce.dto.ProductVerificationRequest;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;
    
    @Override
    public AdminDashboardDTO getDashboard() {

        AdminDashboardDTO dto = new AdminDashboardDTO();

        long active = productRepository.countByStatus(ProductStatus.ACTIVE);

        System.out.println("ACTIVE PRODUCTS = " + active);

        dto.setTotalProducts(active);

        dto.setTotalUsers(userRepository.count());
        dto.setTotalSellers(sellerRepository.count());
        dto.setTotalOrders(orderRepository.count());

        dto.setPendingSellers(
                sellerRepository.countByStatus(SellerStatus.PENDING));

        dto.setPendingProducts(
                productRepository.countByStatus(ProductStatus.PENDING));

        return dto;
    }

    
    @Override
    public List<Seller> getPendingSellers() {

        return sellerRepository.findByStatus(SellerStatus.PENDING);

    }
    
    
    
    @Override
    public String approveSeller(Long sellerId) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() ->
                        new RuntimeException("Seller not found"));

        seller.setStatus(SellerStatus.ACTIVE);

        sellerRepository.save(seller);

        return "Seller Approved Successfully";

    }
    
    
    @Override
    public String rejectSeller(Long sellerId) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() ->
                        new RuntimeException("Seller not found"));

        seller.setStatus(SellerStatus.INACTIVE);

        sellerRepository.save(seller);

        return "Seller Rejected Successfully";

    }
    
    @Override
    public List<Product> getPendingProducts() {

        return productRepository.findByStatus(ProductStatus.PENDING);

    }
     
    
    
    @Override
    public Admin login(String email, String password) {

        Admin admin = adminRepository.findByEmail(email);

        if (admin == null) {
            throw new RuntimeException("Admin not found");
        }

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return admin;
    }
    
    @Override
    public String approveProduct(Long productId,
            ProductVerificationRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        // Seller Price
        Double sellerPrice = product.getSellerPrice();

        if (sellerPrice == null) {
            sellerPrice = product.getSellingPrice();

            product.setSellerPrice(sellerPrice);
        }

        // Discount %
        double adminDiscount = request.getAdminDiscount() == null
                ? 0
                : request.getAdminDiscount();

        // Limit discount between 0 and 100
        adminDiscount = Math.max(0, Math.min(adminDiscount, 100));

        // Calculate values on server
        double discountAmount =
                sellerPrice * adminDiscount / 100;

        double finalSellingPrice =
                sellerPrice - discountAmount;

        product.setAdminDiscount(adminDiscount);
        product.setDiscountAmount(discountAmount);
        product.setFinalSellingPrice(finalSellingPrice);

        // Keep existing profit unless your business logic changes it
        product.setSellerProfit(product.getSellerProfit());

        // Platform contribution equals discount amount
        product.setAdminContribution(discountAmount);

        product.setAdminRemark(request.getAdminRemark());

        product.setStatus(ProductStatus.ACTIVE);

        productRepository.save(product);

        return "Product Approved Successfully";
    }
    
    @Override
    public String rejectProduct(Long productId,
            ProductVerificationRequest request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setAdminRemark(request.getAdminRemark());

        product.setStatus(ProductStatus.REJECTED);

        productRepository.save(product);

        return "Product Rejected Successfully";
    }
}