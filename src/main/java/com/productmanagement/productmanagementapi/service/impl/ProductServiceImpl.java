package com.productmanagement.productmanagementapi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.exception.ResourceAlreadyExistException;
import com.productmanagement.productmanagementapi.model.entity.Product;
import com.productmanagement.productmanagementapi.model.entity.ProductPriceDto;
import com.productmanagement.productmanagementapi.repository.ProductRepository;
import com.productmanagement.productmanagementapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {

        if (productRepository.existsByProductName(product.getProductName())) {
            throw new ResourceAlreadyExistException(
                    "product with name : " + product.getProductName() + " is already existed");
        }
        if (product.getQuantity() != 0) {
            product.setInStock(true);
        }
        return productRepository.save(product);
    }

    @Cacheable(value = "products")
    @Override
    public List<Product> getAllProduct() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }
        return products;

    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id : " + id + " not found"));
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteById(long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(product.getProductId());
    }

    @Override
    public List<Product> addBulkProducts(List<Product> products) {
        products.forEach(product -> {
            if (productRepository.existsByProductName(product.getProductName())) {
                throw new ResourceAlreadyExistException(
                        "product with name : " + product.getProductName() + " is already existed");
            }
            if (product.getQuantity() != 0) {
                product.setInStock(true);
            }
        });
        return productRepository.saveAll(products);
    }

    @Override
    public Product updateProductPrice(long id, BigDecimal newPrice) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id : " + id + " not found"));
        product.setPrice(newPrice);
        return productRepository.save(product);
    }
}
