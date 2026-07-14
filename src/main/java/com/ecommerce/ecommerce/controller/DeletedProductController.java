package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.DeletedProduct;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.service.DeletedProductService;

@RestController
@RequestMapping("/api/deleted-products")
@CrossOrigin(origins = "*")
public class DeletedProductController {

    @Autowired
    private DeletedProductService deletedProductService;
    
    @Autowired
    private ProductRepository productRepository ;
    

    @GetMapping
    public List<DeletedProduct> getAllDeletedProducts() {

        return deletedProductService.getAllDeletedProducts();

    }

    @GetMapping("/seller/{sellerId}")
    public List<Product> getSellerProducts(
            @PathVariable Long sellerId) {

        return productRepository.findBySellerSellerIdAndStatusNot(
                sellerId,
                ProductStatus.DELETED
        );
    }

    @PostMapping("/restore/{deletedProductId}")
    public String restoreProduct(
            @PathVariable Long deletedProductId) {

        return deletedProductService.restoreProduct(deletedProductId);

    }

    @DeleteMapping("/permanent/{deletedProductId}")
    public String permanentDelete(
            @PathVariable Long deletedProductId) {

        return deletedProductService.permanentDelete(deletedProductId);

    }

}