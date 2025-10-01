package com.productmanagement.productmanagementapi.controller;

import com.productmanagement.productmanagementapi.mapper.OrderMapper;
import com.productmanagement.productmanagementapi.model.dto.OrderRequest;
import com.productmanagement.productmanagementapi.model.entity.Order;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.OrderResponse;
import com.productmanagement.productmanagementapi.service.OrderService;
import com.productmanagement.productmanagementapi.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {


    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(summary = "Get all Orders")
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseUtil.ok("all order retrieved", orderMapper.toListOrderResponse(orders));
    }

    @Operation(summary = "Create a Order")
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseUtil.ok("order created", orderResponse);
    }
}
