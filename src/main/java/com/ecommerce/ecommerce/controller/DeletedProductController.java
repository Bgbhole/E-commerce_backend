package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entity.DeletedProduct;
import com.ecommerce.ecommerce.service.DeletedProductService;

@RestController
@RequestMapping("/api/deleted-products")
@CrossOrigin(origins = "*")
public class DeletedProductController {

    @Autowired
    private DeletedProductService deletedProductService;

    @GetMapping
    public List<DeletedProduct> getAllDeletedProducts() {

        return deletedProductService.getAllDeletedProducts();

    }

    @GetMapping("/seller/{sellerId}")
    public List<DeletedProduct> getSellerDeletedProducts(
            @PathVariable Long sellerId) {

        return deletedProductService.getDeletedProductsBySeller(sellerId);

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