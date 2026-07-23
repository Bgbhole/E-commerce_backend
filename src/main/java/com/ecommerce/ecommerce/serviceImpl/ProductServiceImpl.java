	package com.ecommerce.ecommerce.serviceImpl;
	
	import java.io.IOException;
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.web.multipart.MultipartFile;
	
	import com.ecommerce.ecommerce.dto.ProductDTO;
	import com.ecommerce.ecommerce.entity.DeletedProduct;
	import com.ecommerce.ecommerce.entity.Product;
	import com.ecommerce.ecommerce.entity.Seller;
	import com.ecommerce.ecommerce.repository.DeletedProductRepository;
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
		    
		    @Autowired
		    private DeletedProductRepository deletedProductRepository;
	
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
	
		    @Override
		    public void deleteProduct(Long productId) {
	
		        Product product = productRepository.findById(productId)
		                .orElseThrow(() -> new RuntimeException("Product not found"));
	
		        DeletedProduct deleted = new DeletedProduct();
	
		        deleted.setOriginalProductId(product.getProductId());
		        deleted.setProductName(product.getProductName());
		        deleted.setDescription(product.getDescription());
		        deleted.setBrand(product.getBrand());
		        deleted.setCategory(product.getCategory());
		        deleted.setPurchasePrice(product.getPurchasePrice());
		        deleted.setSellingPrice(product.getSellingPrice());
		        deleted.setFinalPrice(product.getFinalPrice());
		        deleted.setProfit(product.getProfit());
		        deleted.setQuantity(product.getQuantity());
	
		        deleted.setImage(product.getImage());
		        deleted.setImage2(product.getImage2());
		        deleted.setImage3(product.getImage3());
		        deleted.setImage4(product.getImage4());
	
		        deleted.setSeller(product.getSeller());
		        deleted.setDeletedAt(java.time.LocalDateTime.now());
	
		        deletedProductRepository.save(deleted);
	
		        // Don't delete the row
		        product.setStatus(ProductStatus.DELETED);
	
		        productRepository.save(product);
		    }
	
			@Override
			public Product addProduct(String productName, String description, String brand, String category,
					double purchasePrice, double sellingPrice, int quantity, double gstPercentage, String color,
					String weight, String warranty, String model, Long sellerId, MultipartFile image, MultipartFile image2,
					MultipartFile image3, MultipartFile image4) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
	
			@Override
			public List<Product> getRelatedProducts(Long productId) {
	
			    Product product = productRepository.findById(productId)
			            .orElseThrow(() -> new RuntimeException("Product Not Found"));
	
			    return productRepository.findByCategoryAndProductIdNot(
			            product.getCategory(),
			            productId
			    );
	
			}
	
			@Override
			public Product getProduct(Long id) {
	
			    return productRepository.findById(id)
			            .orElseThrow(() -> new RuntimeException("Product not found"));
	
			}
	
			@Override
			public Product save(Product product) {
	
			    return productRepository.save(product);
	
			}
			
			@Override
			public Product updateProduct(Long id, Product product) {

			    Product existing = productRepository.findById(id)
			            .orElseThrow(() -> new RuntimeException("Product not found"));

			    // Seller can edit only these
			    existing.setSellingPrice(product.getSellingPrice());
			    existing.setQuantity(product.getQuantity());

			    // Seller Price
			    existing.setSellerPrice(existing.getSellingPrice());

			    // Profit
			    double profit = existing.getSellingPrice() - existing.getPurchasePrice();
			    existing.setProfit(profit);
			    existing.setSellerProfit(profit);

			    // GST
			    double gstAmount =
			            existing.getSellingPrice() * existing.getGstPercentage() / 100;

			    existing.setGstAmount(gstAmount);

			    // Final Price (before Admin Discount)
			    double finalPrice =
			            existing.getSellingPrice() + gstAmount;

			    existing.setFinalPrice(finalPrice);

			    // Admin Discount
			    Double adminDiscount = existing.getAdminDiscount();

			    if (adminDiscount == null) {
			        adminDiscount = 0.0;
			    }

			    double discountAmount =
			            finalPrice * adminDiscount / 100;

			    existing.setDiscountAmount(discountAmount);

			    // Customer Final Price
			    existing.setFinalSellingPrice(finalPrice - discountAmount);

			    // Platform Fee
			    Double platformFee = existing.getPlatformFeePercentage();

			    if (platformFee == null) {
			        platformFee = 0.0;
			    }

			    double platformFeeAmount =
			            existing.getSellingPrice() * platformFee / 100;

			    existing.setPlatformFeeAmount(platformFeeAmount);

			    // Seller Net Profit
			    existing.setSellerNetProfit(
			            existing.getSellingPrice() - platformFeeAmount
			    );

			    // Stock Status
			    if (existing.getQuantity() <= 0) {

			        existing.setStatus(ProductStatus.OUT_OF_STOCK);

			    } else if (existing.getStatus() == ProductStatus.OUT_OF_STOCK) {

			        existing.setStatus(ProductStatus.ACTIVE);

			    }

			    return productRepository.save(existing);
			}
			
	}
	
