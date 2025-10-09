package com.productmanagement.productmanagementapi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.productmanagement.productmanagementapi.repository.OrderItemRepsitory;
import com.productmanagement.productmanagementapi.repository.OutOfInStockProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final OrderItemRepsitory orderItemRepsitory;
    private final OutOfInStockProductRepository outOfInStockProductRepository;

    @Override
    public Product addProduct(Product product, long categoryId) {

        if (productRepository.existsByProductName(product.getProductName())) {
            throw new ResourceAlreadyExistException(
                    "product with name : " + product.getProductName() + " is already existed");
        }
        log.info("Adding product : " + product.getProductName());

        if (product.getQuantity() != 0) {
            product.setInStock(true);
        }

        log.info("Adding product : " + product.getQuantity());
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(
                        "Category with id : " + categoryId + " not found"));

        log.info("Adding product : " + product.getCategory().getCategoryId());
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

    @Transactional
    @Override
    public void deleteById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id : " + id + " not found"));

        // remove the key that have reference to table product first
        orderItemRepsitory.deleteByProduct_productId(product.getProductId());
        outOfInStockProductRepository.deleteByProduct_productId(product.getProductId());

        // this is too

        productRepository.deleteById(product.getProductId());
    }

    @Override
    public List<Product> addBulkProducts(List<ProductRequest> productRequests) {

        List<Product> productsToSave = new ArrayList<>();

        // loop all the product from the requests
        for (ProductRequest productRequest : productRequests) {
            if (productRepository.existsByProductName(productRequest.getProductName())) {
                throw new ResourceAlreadyExistException(
                        "product with name : " + productRequest.getProductName() + " is already existed");
            }

            // convert request to Domain Model
            Product product = productMapper.toEntity(productRequest);
            if (productRequest.getQuantity() != 0) {
                product.setInStock(true);
            }

            // check category if existed in database
            Category existingCategory = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(
                            "Category with id : " + productRequest.getCategoryId() + " not found"));
            product.setCategory(existingCategory);

            // save to the database
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

    @Override
    public Product updateProductById(Long id, Product product) {
        Product existedProduct = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id : " + id + " not found"));
        Category category = categoryRepository.findById(product.getCategory().getCategoryId()).orElseThrow(() -> new NotFoundException("Category with id : " + product.getCategory().getCategoryId() + " not found"));
        existedProduct.setCategory(category);
        existedProduct.setPrice(product.getPrice());
        existedProduct.setProductName(product.getProductName());
        existedProduct.setQuantity(product.getQuantity());
        existedProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(existedProduct);
    }
}
