package com.productmanagement.productmanagementapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.productmanagementapi.mapper.ProductMapper;
import com.productmanagement.productmanagementapi.model.dto.ProductRequest;
import com.productmanagement.productmanagementapi.model.entity.Product;
import com.productmanagement.productmanagementapi.model.entity.ProductPriceDto;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.ProductResponse;
import com.productmanagement.productmanagementapi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Add new product")
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product saveProduct = productMapper.toEntity(productRequest);
        productService.addProduct(saveProduct, productRequest.getCategoryId());
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .code(201)
                .isSuccess(true)
                .message("Product created successfully")
                .payload(productMapper.toDto(saveProduct))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Get a product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable @Positive long id) {
        Product product = productService.getById(id);
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .code(200)
                .isSuccess(true)
                .message("Product retrieved successfully")
                .payload(productMapper.toDto(product))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .code(200)
                .isSuccess(true)
                .message("Products retrieved successfully")
                .payload(productMapper.toDto(products))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a product by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable @Positive long id) {
        productService.deleteById(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(200)
                .isSuccess(true)
                .message("Product deleted successfully")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add bulk products")
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> addBulkProducts(
            @RequestBody @Valid List<ProductRequest> productRequest) {
        List<Product> products = productService.addBulkProducts(productRequest);
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .code(201)
                .isSuccess(true)
                .message("Bulk products created successfully")
                .payload(productMapper.toDto(products))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Update product price by id")
    @PostMapping("/{id}/price")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductPrice(
            @PathVariable @Positive long id,
            @RequestBody @Valid ProductPriceDto productPriceDto) {
        Product updatedProduct = productService.updateProductPrice(id, productPriceDto.getPrice());
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .code(200)
                .isSuccess(true)
                .message("Product price updated successfully")
                .payload(productMapper.toDto(updatedProduct))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get total product count")
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getTotalProductCount() {
        long count = productService.totalProductCount();
        ApiResponse<Long> response = ApiResponse.<Long>builder()
                .code(200)
                .isSuccess(true)
                .message("Total product count retrieved successfully")
                .payload(count)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get products by category id")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategoryId(
            @PathVariable @Positive long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .code(200)
                .isSuccess(true)
                .message("Products by category retrieved successfully")
                .payload(productMapper.toDto(products))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete bulk products by ids")
    @DeleteMapping("/bulk")
    public ResponseEntity<ApiResponse<Void>> deleteBulkProducts(@RequestBody List<@Positive Long> productIds) {
        productService.deleteBulkProducts(productIds);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(200)
                .isSuccess(true)
                .message("Bulk products deleted successfully")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

}
