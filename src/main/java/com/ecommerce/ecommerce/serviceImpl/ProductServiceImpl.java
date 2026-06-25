package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.service.ProductService;

@Service
	public class ProductServiceImpl implements ProductService {

	    @Autowired
	    private ProductRepository productRepository;
	    
	    @Autowired
	    private SellerRepository sellerRepository;

	    public Product addProduct(Product product) {
	        return productRepository.save(product);
	    }

	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }
	    
	    @Override
	    public Product saveProduct(ProductDTO dto) {

	        Seller seller = sellerRepository.findById(dto.getSellerId())
	                .orElseThrow(() -> new RuntimeException("Seller not found"));

	        Product product = new Product();

	        product.setName(dto.getName());
	        product.setDescription(dto.getDescription());
	        product.setFinalPrice(dto.getPrice());
	        product.setCategory(dto.getCategory());
	        product.setImageUrl(dto.getImageUrl());
	        product.setStock(dto.getStock());

	        // Associate seller
	        product.setSeller(seller);

	        return productRepository.save(product);
	    }
	   
	}


