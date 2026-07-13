package com.ecommerce.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entity.DeletedProduct;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.enums.ProductStatus;
import com.ecommerce.ecommerce.repository.DeletedProductRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.service.DeletedProductService;

@Service
public class DeletedProductServiceImpl implements DeletedProductService {

    @Autowired
    private DeletedProductRepository deletedProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<DeletedProduct> getAllDeletedProducts() {

        return deletedProductRepository.findAll();

    }

    @Override
    public List<DeletedProduct> getDeletedProductsBySeller(Long sellerId) {

        return deletedProductRepository.findBySellerSellerId(sellerId);

    }

    @Override
    public String restoreProduct(Long deletedProductId) {

        DeletedProduct deletedProduct =
                deletedProductRepository.findById(deletedProductId)
                .orElseThrow(() ->
                new RuntimeException("Deleted Product Not Found"));

        Product product = new Product();

        product.setProductName(deletedProduct.getProductName());
        product.setDescription(deletedProduct.getDescription());
        product.setBrand(deletedProduct.getBrand());
        product.setCategory(deletedProduct.getCategory());
        product.setPurchasePrice(deletedProduct.getPurchasePrice());
        product.setSellingPrice(deletedProduct.getSellingPrice());
        product.setProfit(deletedProduct.getProfit());
        product.setQuantity(deletedProduct.getQuantity());
        product.setImage(deletedProduct.getImage());
        product.setSeller(deletedProduct.getSeller());

        product.setStatus(ProductStatus.ACTIVE);

        productRepository.save(product);

        deletedProductRepository.delete(deletedProduct);

        return "Product Restored Successfully";

    }

    @Override
    public String permanentDelete(Long deletedProductId) {

        deletedProductRepository.deleteById(deletedProductId);

        return "Product Deleted Permanently";

    }

}