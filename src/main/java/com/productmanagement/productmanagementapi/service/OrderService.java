package com.productmanagement.productmanagementapi.service;

import com.productmanagement.productmanagementapi.model.dto.OrderRequest;
import com.productmanagement.productmanagementapi.model.entity.Order;
import com.productmanagement.productmanagementapi.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<Order> getAllOrders();
}
