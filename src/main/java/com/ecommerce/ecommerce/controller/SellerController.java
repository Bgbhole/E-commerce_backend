package com.ecommerce.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.service.SellerService;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "*")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    

    @PostMapping(value="/register",
    		consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    		public Seller registerSeller(

    		@RequestParam String name,
    		@RequestParam String email,
    		@RequestParam String mobile,
    		@RequestParam String alternateMobile,
    		@RequestParam String password,

    		@RequestParam String businessName,
    		@RequestParam String businessType,

    		@RequestParam String aadharNumber,
    		@RequestParam String businessLicense,

    		@RequestParam String shopName,
    		@RequestParam String shopAddress,

    		@RequestParam String city,
    		@RequestParam String state,
    		@RequestParam String pincode,

    		@RequestParam String category,
    		@RequestParam String productType,

    		@RequestParam MultipartFile shopLogo,
    		@RequestParam MultipartFile shopFrontPhoto,
    		@RequestParam MultipartFile shopInsidePhoto,

    		@RequestParam String gstNumber,
    		@RequestParam String panNumber,

    		@RequestParam String accountHolderName,
    		@RequestParam String accountNumber,
    		@RequestParam String bankName,
    		@RequestParam String ifscCode,

    		@RequestParam String upiId,
    		@RequestParam String pickupAddress,

    		@RequestParam String nomineeName,
    		@RequestParam String nomineeMobile

    		) throws IOException {

    		Seller seller = new Seller();

    		seller.setName(name);
    		seller.setEmail(email);
    		seller.setMobile(mobile);
    		seller.setAlternateMobile(alternateMobile);

    		seller.setPassword(password);

    		seller.setBusinessName(businessName);
    		seller.setBusinessType(businessType);

    		seller.setAadharNumber(aadharNumber);
    		seller.setBusinessLicense(businessLicense);

    		seller.setShopName(shopName);
    		seller.setShopAddress(shopAddress);

    		seller.setCity(city);
    		seller.setState(state);
    		seller.setPincode(pincode);

    		seller.setCategory(category);
    		seller.setProductType(productType);

    		// Create uploads folder if it doesn't exist
    		String uploadDir = "uploads/";

    		File dir = new File(uploadDir);
    		if (!dir.exists()) {
    		    dir.mkdirs();
    		}

    		// Save Shop Logo
    		String logoName = System.currentTimeMillis() + "_" + shopLogo.getOriginalFilename();
    		shopLogo.transferTo(new File(uploadDir + logoName));

    		// Save Shop Front Photo
    		String frontName = System.currentTimeMillis() + "_" + shopFrontPhoto.getOriginalFilename();
    		shopFrontPhoto.transferTo(new File(uploadDir + frontName));

    		// Save Shop Inside Photo
    		String insideName = System.currentTimeMillis() + "_" + shopInsidePhoto.getOriginalFilename();
    		shopInsidePhoto.transferTo(new File(uploadDir + insideName));

    		// Save file names in database
    		seller.setShopLogo(logoName);
    		seller.setShopFrontPhoto(frontName);
    		seller.setShopInsidePhoto(insideName);
    		seller.setGstNumber(gstNumber);
    		seller.setPanNumber(panNumber);

    		seller.setAccountHolderName(accountHolderName);
    		seller.setAccountNumber(accountNumber);
    		seller.setBankName(bankName);
    		seller.setIfscCode(ifscCode);

    		seller.setUpiId(upiId);
    		seller.setPickupAddress(pickupAddress);

    		seller.setNomineeName(nomineeName);
    		seller.setNomineeMobile(nomineeMobile);

    		return sellerService.registerSeller(seller);

    		}
    
    @PostMapping("/login")
    public Seller loginSeller(
    @RequestBody Seller seller){

    return sellerService.loginSeller(

    seller.getEmail(),
    seller.getPassword()

    );

    }
    
    @GetMapping("/{sellerId}")
    public Seller getSeller(

    @PathVariable Long sellerId){

    return sellerService.getSellerById(sellerId);

    }
    
  
    
    @GetMapping
    public List<Seller> getAllSellers(){

    return sellerService.getAllSellers();

    }
    
    @DeleteMapping("/{sellerId}")
    public String deleteSeller(
            @PathVariable Long sellerId) {

        sellerService.deleteSeller(sellerId);

        return "Seller Deleted Successfully";

    }
    
    @GetMapping("/logo/{sellerId}")
    public ResponseEntity<String> getLogo(
            @PathVariable Long sellerId) {

        Seller seller = sellerService.getSellerById(sellerId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(seller.getShopLogo());

    }
    
    @GetMapping("/front/{sellerId}")
    public ResponseEntity<String> getFrontImage(
            @PathVariable Long sellerId) {

        Seller seller = sellerService.getSellerById(sellerId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(seller.getShopFrontPhoto());

    }
    
    @GetMapping("/inside/{sellerId}")
    public ResponseEntity<String> getInsideImage(
            @PathVariable Long sellerId) {

        Seller seller = sellerService.getSellerById(sellerId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(seller.getShopInsidePhoto());

    }

    
   
    
    @PutMapping("/{sellerId}")
    public Seller updateSeller(
            @PathVariable Long sellerId,
            @RequestBody Seller seller) {

        return sellerService.updateSeller(sellerId, seller);

    }
    
    
    
}