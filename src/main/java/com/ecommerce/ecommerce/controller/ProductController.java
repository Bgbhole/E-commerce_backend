package com.ecommerce.ecommerce.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.time.LocalDateTime;

import com.ecommerce.ecommerce.entity.DeletedProduct;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.repository.DeletedProductRepository;
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
	
	@Autowired
	private DeletedProductRepository deletedProductRepository;
	


	@PostMapping(value = "/AddProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product addProduct(

			@RequestParam("productName") String productName, @RequestParam("brand") String brand,
			@RequestParam("category") String category, @RequestParam("description") String description,
			@RequestParam("purchasePrice") double purchasePrice, @RequestParam("sellingPrice") double sellingPrice,
			@RequestParam("gstPercentage") double gstPercentage, @RequestParam("quantity") int quantity,
			@RequestParam("sellerId") Long sellerId,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "image2", required = false) MultipartFile image2,
			@RequestParam(value = "image3", required = false) MultipartFile image3,
			@RequestParam(value = "image4", required = false) MultipartFile image4,
			@RequestParam(value = "color", required = false) String color,
			@RequestParam(value = "weight", required = false) String weight,
			@RequestParam(value = "warranty", required = false) String warranty,
			@RequestParam(value = "model", required = false) String model,
			@RequestParam(value = "size", required = false) String size,
			@RequestParam(value = "material", required = false) String material,
			@RequestParam(value = "ram", required = false) String ram,
			@RequestParam(value = "storage", required = false) String storage,
			@RequestParam(value = "processor", required = false) String processor,
			@RequestParam(value = "battery", required = false) String battery,
			@RequestParam(value = "camera", required = false) String camera,
			@RequestParam(value = "display", required = false) String display,
			@RequestParam(value = "operatingSystem", required = false) String operatingSystem,
			@RequestParam(value = "network", required = false) String network,

			@RequestParam(value = "voltage", required = false) String voltage,
			@RequestParam(value = "power", required = false) String power,
			@RequestParam(value = "connectivity", required = false) String connectivity,

			@RequestParam(value = "fabric", required = false) String fabric,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "fit", required = false) String fit,
			@RequestParam(value = "pattern", required = false) String pattern,
			@RequestParam(value = "sleeve", required = false) String sleeve,
			@RequestParam(value = "washCare", required = false) String washCare,

			@RequestParam(value = "dimensions", required = false) String dimensions,
			@RequestParam(value = "finish", required = false) String finish,
			@RequestParam(value = "assembly", required = false) String assembly,
			@RequestParam(value = "roomType", required = false) String roomType,

			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "publisher", required = false) String publisher,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "pages", required = false) String pages,
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "edition", required = false) String edition,
			@RequestParam(value = "binding", required = false) String binding,
			@RequestParam(value = "publicationYear", required = false) String publicationYear,

			@RequestParam(value = "manufacturer", required = false) String manufacturer,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "expiryDate", required = false) String expiryDate,
			@RequestParam(value = "storageInstruction", required = false) String storageInstruction,
			@RequestParam(value = "veg", required = false) String veg,
			@RequestParam(value = "organic", required = false) String organic,

			@RequestParam(value = "skinType", required = false) String skinType,
			@RequestParam(value = "hairType", required = false) String hairType,
			@RequestParam(value = "ingredients", required = false) String ingredients,
			@RequestParam(value = "benefits", required = false) String benefits,
			@RequestParam(value = "netQuantity", required = false) String netQuantity,

			@RequestParam(value = "sportType", required = false) String sportType,
			@RequestParam(value = "ageGroup", required = false) String ageGroup,

			@RequestParam(value = "toyAge", required = false) String toyAge,
			@RequestParam(value = "batteryRequired", required = false) String batteryRequired,
			@RequestParam(value = "educational", required = false) String educational,
			@RequestParam(value = "safety", required = false) String safety)

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

		product.setImage(image != null && !image.isEmpty() ? image.getBytes() : null);
		product.setImage2(image2 != null && !image2.isEmpty() ? image2.getBytes() : null);
		product.setImage3(image3 != null && !image3.isEmpty() ? image3.getBytes() : null);
		product.setImage4(image4 != null && !image4.isEmpty() ? image4.getBytes() : null);

		product.setColor(color);
		product.setWeight(weight);
		product.setWarranty(warranty);
		product.setModel(model);
		product.setSize(size);
		product.setMaterial(material);
		product.setRam(ram);
		product.setStorage(storage);
		product.setProcessor(processor);
		product.setBattery(battery);
		product.setCamera(camera);
		product.setDisplay(display);
		product.setOperatingSystem(operatingSystem);
		product.setNetwork(network);

		product.setVoltage(voltage);
		product.setPower(power);
		product.setConnectivity(connectivity);

		product.setFabric(fabric);
		product.setGender(gender);
		product.setFit(fit);
		product.setPattern(pattern);
		product.setSleeve(sleeve);
		product.setWashCare(washCare);

		product.setDimensions(dimensions);
		product.setFinish(finish);
		product.setAssembly(assembly);
		product.setRoomType(roomType);

		product.setAuthor(author);
		product.setPublisher(publisher);
		product.setLanguage(language);
		product.setPages(pages);
		product.setIsbn(isbn);
		product.setEdition(edition);
		product.setBinding(binding);
		product.setPublicationYear(publicationYear);

		product.setManufacturer(manufacturer);
		product.setCountry(country);
		product.setExpiryDate(expiryDate);
		product.setStorageInstruction(storageInstruction);
		product.setVeg(veg);
		product.setOrganic(organic);

		product.setSkinType(skinType);
		product.setHairType(hairType);
		product.setIngredients(ingredients);
		product.setBenefits(benefits);
		product.setNetQuantity(netQuantity);

		product.setSportType(sportType);
		product.setAgeGroup(ageGroup);

		product.setToyAge(toyAge);
		product.setBatteryRequired(batteryRequired);
		product.setEducational(educational);
		product.setSafety(safety);

		Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("Seller not found"));

		product.setSeller(seller);

		product.setStatus(ProductStatus.PENDING);

		System.out.println("Color = " + color);
		System.out.println("Weight = " + weight);
		System.out.println("Warranty = " + warranty);
		System.out.println("Model = " + model);
		System.out.println("Size = " + size);
		System.out.println("Material = " + material);
		System.out.println("Product Color = " + product.getColor());
		System.out.println("Product Weight = " + product.getWeight());
		System.out.println("Product Warranty = " + product.getWarranty());
		System.out.println("Product Model = " + product.getModel());
		System.out.println("Product Size = " + product.getSize());
		System.out.println("Product Material = " + product.getMaterial());

		try {

		    Product saved = productRepository.save(product);

		    System.out.println("Saved Successfully");

		    return saved;

		} catch (Exception e) {

		    e.printStackTrace();

		    throw e;

		}
	}

	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

		Product product = productRepository.findById(id).orElseThrow();

		product.setProductName(updatedProduct.getProductName());
		product.setBrand(updatedProduct.getBrand());
		product.setCategory(updatedProduct.getCategory());
		product.setDescription(updatedProduct.getDescription());

		product.setPurchasePrice(updatedProduct.getPurchasePrice());
		product.setSellingPrice(updatedProduct.getSellingPrice());
		product.setGstPercentage(updatedProduct.getGstPercentage());

		// Calculate Profit
		double profit = updatedProduct.getSellingPrice() - updatedProduct.getPurchasePrice();

		product.setProfit(profit);

		// Calculate GST Amount
		double gstAmount = (updatedProduct.getSellingPrice() * updatedProduct.getGstPercentage()) / 100;

		product.setGstAmount(gstAmount);

		// Calculate Final Price
		double finalPrice = updatedProduct.getSellingPrice() + gstAmount;

		product.setFinalPrice(finalPrice);

		product.setQuantity(updatedProduct.getQuantity());

		return productRepository.save(product);

	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {

	    Product product = productRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    DeletedProduct deleted = new DeletedProduct();

	    deleted.setOriginalProductId(product.getProductId());
	    deleted.setProductName(product.getProductName());
	    deleted.setBrand(product.getBrand());
	    deleted.setCategory(product.getCategory());

	    deleted.setImage(product.getImage());

	    deleted.setPurchasePrice(product.getPurchasePrice());
	    deleted.setSellingPrice(product.getSellingPrice());
	    deleted.setFinalPrice(product.getFinalPrice());

	    deleted.setQuantity(product.getQuantity());

	    deleted.setDeletedAt(LocalDateTime.now());

	    deleted.setSeller(product.getSeller());

	    
		// Save to deleted_products table
	    deletedProductRepository.save(deleted);

	    // Hide product from application
	    product.setStatus(ProductStatus.DELETED);

	    productRepository.save(product);

	    return "Product moved to Deleted Products";
	}
	@GetMapping("/seller/{sellerId}")
	public List<Product> getSellerProducts(@PathVariable Long sellerId) {

	    return productRepository.findBySellerSellerIdAndStatus(
	            sellerId,
	            ProductStatus.ACTIVE
	    );
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {

		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	private String saveImage(MultipartFile image) throws Exception {

		if (image == null || image.isEmpty()) {
			return null;
		}

		Path uploadDir = Paths.get("uploads");

		if (!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}

		String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

		Path filePath = uploadDir.resolve(fileName);

		Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}
	


	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {

	    Product product = productRepository.findById(id).orElseThrow();

	    return ResponseEntity.ok(product.getImage());
	}
}
