package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.entity.DeletedProduct;

public interface DeletedProductService {

    List<DeletedProduct> getAllDeletedProducts();

    List<DeletedProduct> getDeletedProductsBySeller(Long sellerId);

    String restoreProduct(Long deletedProductId);

    String permanentDelete(Long deletedProductId);

}