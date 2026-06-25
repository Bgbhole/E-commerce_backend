package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entity.Product;

public interface ProductService {
	
	Product addProduct(Product product);

    List<Product> getAllProducts();

	Product saveProduct(ProductDTO dto);



}
