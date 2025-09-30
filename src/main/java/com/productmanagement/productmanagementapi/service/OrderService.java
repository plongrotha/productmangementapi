package com.productmanagement.productmanagementapi.service;

import com.productmanagement.productmanagementapi.model.dto.OrderRequest;
import com.productmanagement.productmanagementapi.model.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
