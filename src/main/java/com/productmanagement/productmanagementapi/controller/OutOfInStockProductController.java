package com.productmanagement.productmanagementapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.productmanagementapi.model.entity.OutOfInStockProduct;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.service.OutOfInStockProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/out-of-in-stock-products")
public class OutOfInStockProductController {

    private final OutOfInStockProductService inStockProductService;

    @Operation(summary = "Get All prodct that out of stock")
    @GetMapping
    public ResponseEntity<ApiResponse<List<OutOfInStockProduct>>> getAllOutOfInstock() {
        ApiResponse<List<OutOfInStockProduct>> response = ApiResponse.<List<OutOfInStockProduct>>builder()
                .code(HttpStatus.OK.value())
                .isSuccess(true)
                .message("all outOf stock retreive successfully")
                .payload(inStockProductService.allOutOfStock())
                .build();
        return ResponseEntity.ok().body(response);
    }

}
