package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entity.Seller;
import com.ecommerce.ecommerce.enums.SellerStatus;
import com.ecommerce.ecommerce.repository.SellerRepository;
import com.ecommerce.ecommerce.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Seller registerSeller(Seller seller) {

        seller.setPassword(
            passwordEncoder.encode(seller.getPassword())
        );
        
        seller.setStatus(SellerStatus.PENDING);

        return sellerRepository.save(seller);
    }
    
    
    @Override
    public Seller loginSeller(String email, String password) {

        Seller seller = sellerRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Seller not found"));

        if (!passwordEncoder.matches(password, seller.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        if(seller.getStatus() != SellerStatus.ACTIVE){

            throw new RuntimeException(
                "Seller account is inactive."
            );

        }
        
        return seller;
    }
    
	@Override
	public Seller getSellerById(Long sellerId) {
	    return sellerRepository.findById(sellerId).orElse(null);
	}

	@Override
	public List<Seller> getAllSellers() {
	    return sellerRepository.findAll();
	}

	@Override
	public Seller updateSeller(Long sellerId, Seller seller) {

	    Seller existingSeller = sellerRepository.findById(sellerId)
	            .orElseThrow(() -> new RuntimeException("Seller not found"));

	    // Personal Details
	    existingSeller.setName(seller.getName());
	    existingSeller.setEmail(seller.getEmail());
	    existingSeller.setMobile(seller.getMobile());
	    existingSeller.setAlternateMobile(seller.getAlternateMobile());

	    

	    // Business Details
	    existingSeller.setBusinessName(seller.getBusinessName());
	    existingSeller.setBusinessType(seller.getBusinessType());

	    // Shop Details
	    existingSeller.setShopName(seller.getShopName());
	    existingSeller.setShopAddress(seller.getShopAddress());
	    existingSeller.setCity(seller.getCity());
	    existingSeller.setState(seller.getState());
	    existingSeller.setPincode(seller.getPincode());

	    existingSeller.setCategory(seller.getCategory());
	    existingSeller.setProductType(seller.getProductType());
	    existingSeller.setPickupAddress(seller.getPickupAddress());

	    // Documents
	    existingSeller.setGstNumber(seller.getGstNumber());
	    existingSeller.setPanNumber(seller.getPanNumber());
	    existingSeller.setAadharNumber(seller.getAadharNumber());
	    existingSeller.setBusinessLicense(seller.getBusinessLicense());

	    // Bank Details
	    existingSeller.setAccountHolderName(seller.getAccountHolderName());
	    existingSeller.setAccountNumber(seller.getAccountNumber());
	    existingSeller.setBankName(seller.getBankName());
	    existingSeller.setIfscCode(seller.getIfscCode());
	    existingSeller.setUpiId(seller.getUpiId());

	    existingSeller.setBusinessName(seller.getBusinessName());
	    existingSeller.setBusinessType(seller.getBusinessType());
	    // Nominee
	    existingSeller.setNomineeName(seller.getNomineeName());
	    existingSeller.setNomineeMobile(seller.getNomineeMobile());

	    // Keep existing images if not changed
	    if (seller.getShopLogo() != null)
	        existingSeller.setShopLogo(seller.getShopLogo());

	    if (seller.getShopFrontPhoto() != null)
	        existingSeller.setShopFrontPhoto(seller.getShopFrontPhoto());

	    if (seller.getShopInsidePhoto() != null)
	        existingSeller.setShopInsidePhoto(seller.getShopInsidePhoto());

	    return sellerRepository.save(existingSeller);
	}

	@Override
	public void deleteSeller(Long sellerId) {
	    sellerRepository.deleteById(sellerId);
	}

	
}
