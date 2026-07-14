package com.ecommerce.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ecommerce.ecommerce.repository.OrderItemRepository;
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

import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.service.ProductService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

	private final String uploadDir = "uploads";
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SellerRepository sellerRepository;
    
	@Autowired
	private ProductService productService;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@PostMapping(value = "/AddProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product addProduct(

			
			
	        // Basic
	        @RequestParam String productName,
	        @RequestParam String description,
	        @RequestParam String brand,
	        @RequestParam String category,
	        @RequestParam double purchasePrice,
	        @RequestParam double sellingPrice,
	        @RequestParam int quantity,
	        @RequestParam double gstPercentage,

	        // Common
	        @RequestParam(required = false) String color,
	        @RequestParam(required = false) String weight,
	        @RequestParam(required = false) String warranty,
	        @RequestParam(required = false) String model,

	        // Mobile
	        @RequestParam(required = false) String ram,
	        @RequestParam(required = false) String storage,
	        @RequestParam(required = false) String processor,
	        @RequestParam(required = false) String battery,
	        @RequestParam(required = false) String camera,
	        @RequestParam(required = false) String display,
	        @RequestParam(required = false) String operatingSystem,
	        @RequestParam(required = false) String network,

	        // Electronics
	        @RequestParam(required = false) String voltage,
	        @RequestParam(required = false) String power,
	        @RequestParam(required = false) String connectivity,

	        // Fashion
	        @RequestParam(required = false) String size,
	        @RequestParam(required = false) String material,
	        @RequestParam(required = false) String fabric,
	        @RequestParam(required = false) String gender,
	        @RequestParam(required = false) String fit,
	        @RequestParam(required = false) String pattern,
	        @RequestParam(required = false) String sleeve,
	        @RequestParam(required = false) String washCare,

	        // Furniture
	        @RequestParam(required = false) String dimensions,
	        @RequestParam(required = false) String finish,
	        @RequestParam(required = false) String assembly,
	        @RequestParam(required = false) String roomType,

	        // Books
	        @RequestParam(required = false) String author,
	        @RequestParam(required = false) String publisher,
	        @RequestParam(required = false) String language,
	        @RequestParam(required = false) Integer pages,
	        @RequestParam(required = false) String isbn,
	        @RequestParam(required = false) String edition,
	        @RequestParam(required = false) String binding,
	        @RequestParam(required = false) Integer publicationYear,

	        // Grocery
	        @RequestParam(required = false) String manufacturer,
	        @RequestParam(required = false) String expiryDate,
	        @RequestParam(required = false) String storageInstruction,
	        @RequestParam(required = false) String country,
	        @RequestParam(required = false) String organic,
	        @RequestParam(required = false) String veg,

	        // Beauty
	        @RequestParam(required = false) String skinType,
	        @RequestParam(required = false) String hairType,
	        @RequestParam(required = false) String ingredients,
	        @RequestParam(required = false) String benefits,
	        @RequestParam(required = false) String netQuantity,

	        // Sports
	        @RequestParam(required = false) String sportType,
	        @RequestParam(required = false) String ageGroup,

	        // Toys
	        @RequestParam(required = false) String toyAge,
	        @RequestParam(required = false) String batteryRequired,
	        @RequestParam(required = false) String educational,
	        @RequestParam(required = false) String safety,

	        // Seller
	        @RequestParam Long sellerId,

	        // Images
	        @RequestParam MultipartFile image,
	        @RequestParam(required = false) MultipartFile image2,
	        @RequestParam(required = false) MultipartFile image3,
	        @RequestParam(required = false) MultipartFile image4

	) throws IOException {

	    Product product = new Product();

	    product.setProductName(productName);
	    product.setDescription(description);
	    product.setBrand(brand);
	    product.setCategory(category);

	    product.setPurchasePrice(purchasePrice);
	    product.setSellingPrice(sellingPrice);
	    product.setQuantity(quantity);
	    product.setGstPercentage(gstPercentage);

	    double profit = sellingPrice - purchasePrice;
	    double gstAmount = sellingPrice * gstPercentage / 100;
	    double finalPrice = sellingPrice + gstAmount;

	    product.setProfit(profit);
	    product.setGstAmount(gstAmount);
	    product.setFinalPrice(finalPrice);

	    // Common
	    product.setColor(color);
	    product.setWeight(weight);
	    product.setWarranty(warranty);
	    product.setModel(model);

	    // Mobile
	    product.setRam(ram);
	    product.setStorage(storage);
	    product.setProcessor(processor);
	    product.setBattery(battery);
	    product.setCamera(camera);
	    product.setDisplay(display);
	    product.setOperatingSystem(operatingSystem);
	    product.setNetwork(network);

	    // Electronics
	    product.setVoltage(voltage);
	    product.setPower(power);
	    product.setConnectivity(connectivity);

	    // Fashion
	    product.setSize(size);
	    product.setMaterial(material);
	    product.setFabric(fabric);
	    product.setGender(gender);
	    product.setFit(fit);
	    product.setPattern(pattern);
	    product.setSleeve(sleeve);
	    product.setWashCare(washCare);

	    // Furniture
	    product.setDimensions(dimensions);
	    product.setFinish(finish);
	    product.setAssembly(assembly);
	    product.setRoomType(roomType);

	    // Books
	    product.setAuthor(author);
	    product.setPublisher(publisher);
	    product.setLanguage(language);
	    product.setPages(pages);
	    product.setIsbn(isbn);
	    product.setEdition(edition);
	    product.setBinding(binding);
	    product.setPublicationYear(publicationYear);

	    // Grocery
	    product.setManufacturer(manufacturer);
	    product.setExpiryDate(expiryDate);
	    product.setStorageInstruction(storageInstruction);
	    product.setCountry(country);
	    product.setOrganic(organic);
	    product.setVeg(veg);

	    // Beauty
	    product.setSkinType(skinType);
	    product.setHairType(hairType);
	    product.setIngredients(ingredients);
	    product.setBenefits(benefits);
	    product.setNetQuantity(netQuantity);

	    // Sports
	    product.setSportType(sportType);
	    product.setAgeGroup(ageGroup);

	    // Toys
	    product.setToyAge(toyAge);
	    product.setBatteryRequired(batteryRequired);
	    product.setEducational(educational);
	    product.setSafety(safety);

	    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

	    Path uploadPath = Paths.get(uploadDir);

	    Files.createDirectories(uploadPath);

	    Files.copy(
	            image.getInputStream(),
	            uploadPath.resolve(fileName),
	            StandardCopyOption.REPLACE_EXISTING
	    );

	    product.setImage(fileName);

	    Seller seller = sellerRepository.findById(sellerId)
	            .orElseThrow(() -> new RuntimeException("Seller Not Found"));

	    product.setSeller(seller);
	    product.setStatus(ProductStatus.PENDING);

	    return productRepository.save(product);
	}
    
	@GetMapping("/all")
	public List<Product> getAllProducts() {

	    return productRepository.findByStatusAndSellerStatus(
	            ProductStatus.ACTIVE,
	            SellerStatus.ACTIVE
	    );

	}

    
    
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {

	    return productRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product Not Found"));

	}
    
    
    @GetMapping("/seller/{sellerId}")
    public List<Product> getSellerProducts(
            @PathVariable Long sellerId) {

        return productRepository.findBySellerSellerIdAndStatusNot(
                sellerId,
                ProductStatus.DELETED
        );

    }
    
    @GetMapping("/category/{category}")
    public List<Product> getCategoryProducts(
            @PathVariable String category) {

        return productRepository.findByCategory(category);

    }
    
    @PutMapping("/update/{productId}")
    public Product updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product) {

        Product old = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        // Basic
        old.setProductName(product.getProductName());
        old.setDescription(product.getDescription());
        old.setBrand(product.getBrand());
        old.setCategory(product.getCategory());

        old.setPurchasePrice(product.getPurchasePrice());
        old.setSellingPrice(product.getSellingPrice());

        old.setQuantity(product.getQuantity());

        old.setGstPercentage(product.getGstPercentage());
        old.setProfit(product.getProfit());
        old.setGstAmount(product.getGstAmount());
        old.setFinalPrice(product.getFinalPrice());

        // Common
        old.setColor(product.getColor());
        old.setWeight(product.getWeight());
        old.setWarranty(product.getWarranty());
        old.setModel(product.getModel());

        // Mobile
        old.setRam(product.getRam());
        old.setStorage(product.getStorage());
        old.setProcessor(product.getProcessor());
        old.setBattery(product.getBattery());
        old.setCamera(product.getCamera());
        old.setDisplay(product.getDisplay());
        old.setOperatingSystem(product.getOperatingSystem());
        old.setNetwork(product.getNetwork());

        // Electronics
        old.setVoltage(product.getVoltage());
        old.setPower(product.getPower());
        old.setConnectivity(product.getConnectivity());

        // Fashion
        old.setSize(product.getSize());
        old.setMaterial(product.getMaterial());
        old.setFabric(product.getFabric());
        old.setGender(product.getGender());
        old.setFit(product.getFit());
        old.setPattern(product.getPattern());
        old.setSleeve(product.getSleeve());
        old.setWashCare(product.getWashCare());

        // Furniture
        old.setDimensions(product.getDimensions());
        old.setFinish(product.getFinish());
        old.setAssembly(product.getAssembly());
        old.setRoomType(product.getRoomType());

        // Books
        old.setAuthor(product.getAuthor());
        old.setPublisher(product.getPublisher());
        old.setLanguage(product.getLanguage());
        old.setPages(product.getPages());
        old.setIsbn(product.getIsbn());
        old.setEdition(product.getEdition());
        old.setBinding(product.getBinding());
        old.setPublicationYear(product.getPublicationYear());

        // Grocery
        old.setManufacturer(product.getManufacturer());
        old.setExpiryDate(product.getExpiryDate());
        old.setStorageInstruction(product.getStorageInstruction());
        old.setCountry(product.getCountry());
        old.setOrganic(product.getOrganic());
        old.setVeg(product.getVeg());

        // Beauty
        old.setSkinType(product.getSkinType());
        old.setHairType(product.getHairType());
        old.setIngredients(product.getIngredients());
        old.setBenefits(product.getBenefits());
        old.setNetQuantity(product.getNetQuantity());

        // Sports
        old.setSportType(product.getSportType());
        old.setAgeGroup(product.getAgeGroup());

        // Toys
        old.setToyAge(product.getToyAge());
        old.setBatteryRequired(product.getBatteryRequired());
        old.setEducational(product.getEducational());
        old.setSafety(product.getSafety());

        // If quantity becomes available again
        if (old.getQuantity() > 0 && old.getStatus() == ProductStatus.OUT_OF_STOCK) {
            old.setStatus(ProductStatus.ACTIVE);
        }

        // If quantity becomes zero
        if (old.getQuantity() <= 0) {
            old.setStatus(ProductStatus.OUT_OF_STOCK);
        }

        return productRepository.save(old);
    }
    
    @GetMapping("/image/{id}")
    public ResponseEntity<String> getImage(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (product.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage());
    }
    
    
    @GetMapping("/image2/{id}")
    public ResponseEntity<String> getImage2(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (product.getImage2() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage2());
    }
    
    
    @GetMapping("/image3/{id}")
    public ResponseEntity<String> getImage3(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (product.getImage3() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage3());
    }
    
    
    @GetMapping("/image4/{id}")
    public ResponseEntity<String> getImage4(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        if (product.getImage4() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(product.getImage4());
    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok("Product Deleted Successfully");
    }
    
    @GetMapping("/pending-products")
    public List<Product> getPendingProducts() {

        return productRepository.findByStatus(ProductStatus.PENDING);

    }
    
    @PutMapping("/approve-product/{id}")
    public ResponseEntity<String> approveProduct(
            @PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setStatus(ProductStatus.ACTIVE);

        productRepository.save(product);

        return ResponseEntity.ok("Product Approved Successfully");
    }
    
    @PutMapping("/reject-product/{id}")
    public ResponseEntity<String> rejectProduct(
            @PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setStatus(ProductStatus.REJECTED);

        productRepository.save(product);

        return ResponseEntity.ok("Product Rejected Successfully");
    }
    
    @GetMapping("/item-image/{orderItemId}")
    public ResponseEntity<String> getOrderItemImage(@PathVariable Long orderItemId) {

        OrderItem item = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order Item Not Found"));

        if (item.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(item.getImage());
    }
    
}