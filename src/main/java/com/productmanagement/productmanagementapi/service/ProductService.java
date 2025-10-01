package com.productmanagement.productmanagementapi.service;

import java.math.BigDecimal;
import java.util.List;

import com.productmanagement.productmanagementapi.model.dto.ProductRequest;
import com.productmanagement.productmanagementapi.model.entity.Product;

public interface ProductService {

    Product addProduct(Product product, long categoryId);

    List<Product> getAllProduct();

    Product getById(long id);

    void deleteById(long id);

    List<Product> addBulkProducts(List<ProductRequest> productRequests);

    Product updateProductPrice(long id, BigDecimal newPrice);

    long totalProductCount();

    List<Product> getProductsByCategoryId(long categoryId);

    void deleteBulkProducts(List<Long> productIds);

    List<Product> getAllProductsInStockIsFalse();
}
