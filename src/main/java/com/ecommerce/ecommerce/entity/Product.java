package com.ecommerce.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.ecommerce.ecommerce.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @Column(length = 1000)
    private String description;

    private String brand;

    private String category;

    private double purchasePrice;

    private double sellingPrice;

    private double profit;

    private int quantity;
    
    private Double sellerPrice;

    private Double adminDiscount;

    private Double discountAmount;

    private Double finalSellingPrice;

    private Double sellerProfit;

    private Double adminContribution;
    
    private String adminRemark;

    private double gstPercentage;

    private double gstAmount;
    private String image;

    private String image2;

    private String image3;

    private String image4;
  
    
    private double finalPrice;
    
    private String color;

    private String weight;

    private String warranty;

    private String model;

    private String size;

    private String material;
    
    private String ram;
    private String storage;
    private String processor;
    private String battery;
    private String camera;
    private String display;
    private String operatingSystem;
    private String network;

    private String voltage;
    private String power;
    private String connectivity;

    private String fabric;
    private String gender;
    private String fit;
    private String pattern;
    private String sleeve;
    private String washCare;

    private String dimensions;
    private String finish;
    private String assembly;
    private String roomType;
    
    //book
    private String author;
    private String publisher;
    private String language;
    private Integer pages;
    private String isbn;
    private String edition;
    private String binding;
    private Integer publicationYear;
    
    //Grocery
    private String manufacturer;
    private String country;
    private String expiryDate;
    private String storageInstruction;
    private String veg;
    private String organic;
    
    //Beauty
    private String skinType;
    private String hairType;
    private String ingredients;
    private String benefits;
    private String netQuantity;
    
    //Sports
    private String sportType;
    private String ageGroup;
    
    //Toys
    private String toyAge;
    private String batteryRequired;
    private String educational;
    private String safety;
    
    
    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    @Column(name = "total_reviews")
    private Integer totalReviews = 0;
    
    
    
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    
    

    @ManyToOne
    @JoinColumn(name="seller_id")
    @JsonIgnoreProperties({
        "products",
        "password",
        "otp",
        "otpExpiry"
    })
    private Seller seller;
    
    
    
    
	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
	    this.quantity = quantity;
	}

	public double getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(double gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	public double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(double gstAmount) {
		this.gstAmount = gstAmount;
	}



	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	
	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getSleeve() {
		return sleeve;
	}

	public void setSleeve(String sleeve) {
		this.sleeve = sleeve;
	}

	public String getWashCare() {
		return washCare;
	}

	public void setWashCare(String washCare) {
		this.washCare = washCare;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getAssembly() {
		return assembly;
	}

	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getPages() {
		return pages;
	}

	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}


	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStorageInstruction() {
		return storageInstruction;
	}

	public void setStorageInstruction(String storageInstruction) {
		this.storageInstruction = storageInstruction;
	}

	public String getVeg() {
		return veg;
	}

	public void setVeg(String veg) {
		this.veg = veg;
	}

	public String getOrganic() {
		return organic;
	}

	public void setOrganic(String organic) {
		this.organic = organic;
	}

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getNetQuantity() {
		return netQuantity;
	}

	public void setNetQuantity(String netQuantity) {
		this.netQuantity = netQuantity;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getToyAge() {
		return toyAge;
	}

	public void setToyAge(String toyAge) {
		this.toyAge = toyAge;
	}

	public String getBatteryRequired() {
		return batteryRequired;
	}

	public void setBatteryRequired(String batteryRequired) {
		this.batteryRequired = batteryRequired;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	

	public void setPages(Integer pages) {
	    this.pages = pages;
	}

	public void setPublicationYear(Integer publicationYear) {
	    this.publicationYear = publicationYear;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}

	public Double getSellerPrice() {
	    return sellerPrice;
	}

	public void setSellerPrice(Double sellerPrice) {
	    this.sellerPrice = sellerPrice;
	}

	public Double getSellerProfit() {
	    return sellerProfit;
	}

	public void setSellerProfit(Double sellerProfit) {
	    this.sellerProfit = sellerProfit;
	}

	public Double getAdminDiscount() {
	    return adminDiscount;
	}

	public void setAdminDiscount(Double adminDiscount) {
	    this.adminDiscount = adminDiscount;
	}

	public Double getDiscountAmount() {
	    return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
	    this.discountAmount = discountAmount;
	}

	public Double getFinalSellingPrice() {
	    return finalSellingPrice;
	}

	public void setFinalSellingPrice(Double finalSellingPrice) {
	    this.finalSellingPrice = finalSellingPrice;
	}

	public Double getAdminContribution() {
	    return adminContribution;
	}

	public void setAdminContribution(Double adminContribution) {
	    this.adminContribution = adminContribution;
	}

	public String getAdminRemark() {
	    return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
	    this.adminRemark = adminRemark;
	}

	
	
    
    
}