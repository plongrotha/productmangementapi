package com.productmanagement.productmanagementapi.service;

import java.math.BigDecimal;
import java.util.List;

import com.productmanagement.productmanagementapi.model.entity.Product;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProduct();

    Product getById(long id);

    void deleteById(long id);

    List<Product> addBulkProducts(List<Product> products);

    Product updateProductPrice(long id, BigDecimal newPrice);

}
