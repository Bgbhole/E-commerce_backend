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
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.enums.SellerStatus;


@Service
	public class ProductServiceImpl implements ProductService {

	    @Autowired
	    private ProductRepository productRepository;
	    
	    
	    
	    @Autowired
	    private SellerRepository sellerRepository;

	    public Product addProduct(Product product) {
	        return productRepository.save(product);
	    }

	    @Override
	    public List<Product> getAllProducts() {

	        return productRepository.findByStatusAndSellerStatus(
	                ProductStatus.ACTIVE,
	                SellerStatus.ACTIVE
	        );
	    }
	    
	    @Override
	    public Product saveProduct(ProductDTO dto) {

	        Seller seller = sellerRepository.findById(dto.getSellerId())
	                .orElseThrow(() -> new RuntimeException("Seller not found"));

	        Product product = new Product();

	        product.setProductName(dto.getName());
	        product.setDescription(dto.getDescription());
	        product.setCategory(dto.getCategory());
	        product.setFinalPrice(dto.getPrice());
	        product.setQuantity(dto.getQuantity());

	        product.setSeller(seller);

	        return productRepository.save(product);
	    }
	   
	}


