package com.ecommerce.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entity.Product;

public interface ProductService {
	
	Product addProduct(
	        String productName,
	        String description,
	        String brand,
	        String category,
	        double purchasePrice,
	        double sellingPrice,
	        int quantity,
	        double gstPercentage,
	        String color,
	        String weight,
	        String warranty,
	        String model,
	        Long sellerId,
	        MultipartFile image,
	        MultipartFile image2,
	        MultipartFile image3,
	        MultipartFile image4
	) throws IOException;

    List<Product> getAllProducts();

	Product saveProduct(ProductDTO dto);

	void deleteProduct(Long productId);
	
	List<Product> getRelatedProducts(
	        Long productId
	);

	Product getProduct(Long id);

	Product save(Product product);

	Product updateProduct(Long id, Product product);



}
