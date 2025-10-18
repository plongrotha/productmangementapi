package com.productmanagement.productmanagementapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.productmanagement.productmanagementapi.mapper.ProductMapper;
import com.productmanagement.productmanagementapi.model.dto.ProductRequest;
import com.productmanagement.productmanagementapi.model.entity.Product;
import com.productmanagement.productmanagementapi.model.entity.ProductPriceDto;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.ProductResponse;
import com.productmanagement.productmanagementapi.service.ProductService;
import com.productmanagement.productmanagementapi.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Add new product")
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product saveProduct = productMapper.toEntity(productRequest);
        Product createdProduct = productService.addProduct(saveProduct, productRequest.getCategoryId());
        return ResponseUtil.ok("product created successfully", productMapper.toProductResponse(createdProduct));
    }

    @Operation(summary = "Get a product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable @Positive Long id) {
        Product product = productService.getById(id);
        return ResponseUtil.ok("Product retrieved successfully", productMapper.toProductResponse(product));
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return ResponseUtil.ok("Products retrieved successfully", productMapper.toDto(products));
    }

    @Operation(summary = "Delete a product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable @Positive Long id) {
        productService.deleteById(id);
        return ResponseUtil.ok("Product deleted successfully");
    }

    @Operation(summary = "Add bulk products")
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> addBulkProducts(
            @RequestBody @Valid List<ProductRequest> productRequest) {
        List<Product> products = productService.addBulkProducts(productRequest);
        return ResponseUtil.ok("Bulk products created successfully", productMapper.toDto(products));
    }

    @Operation(summary = "Update product price by id")
    @PatchMapping("/{id}/price")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductPrice(@PathVariable @Positive Long id,
            @RequestBody @Valid ProductPriceDto productPriceDto) {
        Product updatedProduct = productService.updateProductPrice(id, productPriceDto.getPrice());
        return ResponseUtil.ok("Product price updated successfully", productMapper.toProductResponse(updatedProduct));
    }

    @Operation(summary = "Get total product count")
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getTotalProductCount() {
        Long count = productService.totalProductCount();
        return ResponseUtil.ok("Total product count retrieved successfully", count);
    }

    @Operation(summary = "Get products by category id")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategoryId(
            @PathVariable @Positive Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return ResponseUtil.ok("Products by category retrieved successfully", productMapper.toDto(products));
    }

    @Operation(summary = "Delete bulk products by ids")
    @DeleteMapping("/bulk")
    public ResponseEntity<ApiResponse<Void>> deleteBulkProducts(
            @RequestBody @NotEmpty List<@Positive Long> productIds) {
        productService.deleteBulkProducts(productIds);
        return ResponseUtil.ok("Bulk products deleted successfully");
    }

    @Operation(summary = "Get all Product that not have in Stock")
    @GetMapping("/notInStock")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProductNotInStock() {
        return ResponseUtil.ok("product not inStock are retrieve successfully",
                productMapper.toDto(productService.getAllProductsInStockIsFalse()));
    }

    @Operation(summary = "Update a product By Id")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductById(@PathVariable @Positive Long id,
            @RequestBody @Valid ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product saveUpdate = productService.updateProductById(id, product);
        return ResponseUtil.ok("update product successfully", productMapper.toProductResponse(saveUpdate));
    }
}
