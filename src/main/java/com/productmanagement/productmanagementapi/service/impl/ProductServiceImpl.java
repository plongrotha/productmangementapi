package com.productmanagement.productmanagementapi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.exception.ResourceAlreadyExistException;
import com.productmanagement.productmanagementapi.mapper.ProductMapper;
import com.productmanagement.productmanagementapi.model.dto.ProductRequest;
import com.productmanagement.productmanagementapi.model.entity.Category;
import com.productmanagement.productmanagementapi.model.entity.Product;
import com.productmanagement.productmanagementapi.repository.CategoryRepository;
import com.productmanagement.productmanagementapi.repository.ProductRepository;
import com.productmanagement.productmanagementapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product addProduct(Product product, long categoryId) {

        if (productRepository.existsByProductName(product.getProductName())) {
            throw new ResourceAlreadyExistException(
                    "product with name : " + product.getProductName() + " is already existed");
        }
        if (product.getQuantity() != 0) {
            product.setInStock(true);
        }
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(
                        "Category with id : " + categoryId + " not found"));
        product.setCategory(existingCategory);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }
        return products;

    }

    @Override
    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id : " + id + " not found"));
    }

    @Override
    public void deleteById(long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(product.getProductId());
    }

    @Override
    public List<Product> addBulkProducts(List<ProductRequest> productRequests) {
        List<Product> productsToSave = new ArrayList<>();
        for (ProductRequest productRequest : productRequests) {
            if (productRepository.existsByProductName(productRequest.getProductName())) {
                throw new ResourceAlreadyExistException(
                        "product with name : " + productRequest.getProductName() + " is already existed");
            }
            Product product = productMapper.toEntity(productRequest);
            if (productRequest.getQuantity() != 0) {
                product.setInStock(true);
            }
            Category existingCategory = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(
                            "Category with id : " + productRequest.getCategoryId() + " not found"));
            product.setCategory(existingCategory);
            productsToSave.add(product);
        }
        return productRepository.saveAll(productsToSave);
    }

    @Override
    public Product updateProductPrice(long id, BigDecimal newPrice) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id : " + id + " not found"));
        product.setPrice(newPrice);
        return productRepository.save(product);
    }

    @Override
    public long totalProductCount() {
        return productRepository.count();
    }

    @Override
    public List<Product> getProductsByCategoryId(long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with id : " + categoryId + " not found"));
        return productRepository.findByCategory_CategoryId(category.getCategoryId());
    }

    @Override
    public void deleteBulkProducts(List<Long> productIds) {
        productIds.forEach(id -> {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                throw new NotFoundException("Product with id : " + id + " not found");
            }
        });
    }

    @Override
    public List<Product> getAllProductsInStockIsFalse() {
        return  productRepository.findAll().stream().filter(product -> !product.isInStock()).collect(Collectors.toList());
    }
}
