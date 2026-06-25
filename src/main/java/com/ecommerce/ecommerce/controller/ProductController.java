package com.ecommerce.ecommerce.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.StandardCopyOption;


import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private SellerRepository sellerRepository;

    @PostMapping(value="/AddProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(

            @RequestParam("productName") String productName,
            @RequestParam("brand") String brand,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("purchasePrice") double purchasePrice,
            @RequestParam("sellingPrice") double sellingPrice,
            @RequestParam("gstPercentage") double gstPercentage,
            @RequestParam("quantity") int quantity,
            @RequestParam("sellerId") Long sellerId,
            @RequestParam("image") MultipartFile image)

    throws Exception {

        Product product = new Product();

        product.setProductName(productName);
        product.setBrand(brand);
        product.setCategory(category);
        product.setDescription(description);

        product.setPurchasePrice(purchasePrice);
        product.setSellingPrice(sellingPrice);

        // Profit
        double profit = sellingPrice - purchasePrice;
        product.setProfit(profit);

        // GST
        product.setGstPercentage(gstPercentage);

        double gstAmount = (sellingPrice * gstPercentage) / 100;
        product.setGstAmount(gstAmount);

        // Final Price customer pays
        double finalPrice = sellingPrice + gstAmount;
        product.setFinalPrice(finalPrice);

        product.setQuantity(quantity);
        if (!image.isEmpty()) {

            Path uploadDir = Paths.get("src/main/resources/static/images");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Unique file name
            String fileName =
                    System.currentTimeMillis() + "_"
                    + image.getOriginalFilename();

            Path filePath = uploadDir.resolve(fileName);

            Files.copy(
                    image.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            product.setImage(fileName);
        }
            

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        product.setSeller(seller);

        return productRepository.save(product);
    }
    

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @PutMapping("/update/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct){

        Product product = productRepository.findById(id).orElseThrow();

        product.setProductName(updatedProduct.getProductName());
        product.setDescription(updatedProduct.getDescription());
        product.setSellingPrice(updatedProduct.getSellingPrice());
        product.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(product);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id){

        productRepository.deleteById(id);

        return "Deleted";
    }
    
    @GetMapping("/seller/{sellerId}")
    public List<Product> getSellerProducts(
            @PathVariable Long sellerId){

        return productRepository.findBySellerSellerId(sellerId);

    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){

        return productRepository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Product not found"));
    }
}


