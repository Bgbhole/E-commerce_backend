package com.ecommerce.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.enums.SellerStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SellerRepository sellerRepository;
    
	@Autowired
	private ProductService productService;
	
    @PostMapping(value="/AddProduct",
    		consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    		public Product addProduct(

    		@RequestParam String productName,
    		@RequestParam String description,
    		@RequestParam String brand,
    		@RequestParam String category,
    		@RequestParam double purchasePrice,
    		@RequestParam double sellingPrice,
    		@RequestParam double finalPrice,
    		@RequestParam double profit,
    		@RequestParam int quantity,
    		@RequestParam double gstPercentage,
    		@RequestParam double gstAmount,

    		@RequestParam(required=false) String color,
    		@RequestParam(required=false) String weight,
    		@RequestParam(required=false) String warranty,
    		@RequestParam(required=false) String model,

    		@RequestParam Long sellerId,

    		@RequestParam MultipartFile image,

    		@RequestParam(required=false) MultipartFile image2,
    		@RequestParam(required=false) MultipartFile image3,
    		@RequestParam(required=false) MultipartFile image4

    		) throws IOException {

    		Product product = new Product();

    		product.setProductName(productName);
    		product.setDescription(description);
    		product.setBrand(brand);
    		product.setCategory(category);

    		product.setPurchasePrice(purchasePrice);
    		product.setSellingPrice(sellingPrice);
    		product.setFinalPrice(finalPrice);
    		product.setProfit(profit);

    		product.setQuantity(quantity);

    		product.setGstPercentage(gstPercentage);
    		product.setGstAmount(gstAmount);

    		product.setColor(color);
    		product.setWeight(weight);
    		product.setWarranty(warranty);
    		product.setModel(model);

    		product.setImage(image.getBytes());

    		if(image2!=null && !image2.isEmpty())
    		product.setImage2(image2.getBytes());

    		if(image3!=null && !image3.isEmpty())
    		product.setImage3(image3.getBytes());

    		if(image4!=null && !image4.isEmpty())
    		product.setImage4(image4.getBytes());

    		Seller seller = sellerRepository.findById(sellerId)
    		        .orElseThrow(() -> new RuntimeException("Seller Not Found"));

    		product.setSeller(seller);

    		return productRepository.save(product);
    		
    
    		}
    
    @GetMapping("/all")
    public List<Product> getAllProducts(){

    	return productRepository.findByStatusAndSellerStatus(
    	        ProductStatus.ACTIVE,
    	        SellerStatus.ACTIVE
    	);

    }
    
    @GetMapping("/{id}")
    public Product getProduct(

    @PathVariable Long id){

    	return productRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Product Not Found"));

    }
    
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(

    @PathVariable Long id){

    	Product product = productRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Product Not Found"));

    return ResponseEntity.ok()

    .header(HttpHeaders.CONTENT_TYPE,
    MediaType.IMAGE_JPEG_VALUE)

    .body(product.getImage());

    }
    
    @GetMapping("/seller/{sellerId}")
    public List<Product> getSellerProducts(
            @PathVariable Long sellerId) {

    	return productRepository.findBySellerSellerId(sellerId);

    }
    
    @GetMapping("/category/{category}")
    public List<Product> getCategoryProducts(
            @PathVariable String category) {

    	return productRepository.findByCategory(category);
    }
    
    @PutMapping("/{productId}")
    public Product updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product) {

    	Product old = productRepository.findById(productId)
    	        .orElseThrow(() -> new RuntimeException("Product Not Found"));

    	old.setProductName(product.getProductName());
    	old.setDescription(product.getDescription());
    	old.setCategory(product.getCategory());
    	old.setFinalPrice(product.getFinalPrice());
    	old.setQuantity(product.getQuantity());

    	return productRepository.save(old);

    }
    
    @GetMapping("/image2/{id}")
    public ResponseEntity<byte[]> getImage2(
            @PathVariable Long id) {

    	Product product = productRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage2());

    }
    
    @GetMapping("/image3/{id}")
    public ResponseEntity<byte[]> getImage3(
            @PathVariable Long id) {

    	Product product = productRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage3());

    }
    
    @GetMapping("/image4/{id}")
    public ResponseEntity<byte[]> getImage4(
            @PathVariable Long id) {

    	Product product = productRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage4());

    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok("Product deleted successfully");
    }
    
}