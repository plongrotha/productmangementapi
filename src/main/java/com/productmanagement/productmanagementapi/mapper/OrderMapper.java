package com.productmanagement.productmanagementapi.mapper;

import com.productmanagement.productmanagementapi.model.entity.OrderItem;
import com.productmanagement.productmanagementapi.model.response.OrderItemResponse;
import com.productmanagement.productmanagementapi.model.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.productmanagement.productmanagementapi.model.entity.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(target = "phone", source = "customer.phone")
    @Mapping(target = "customerName", source = "customer.customerName")
    OrderResponse toOrderResponse(Order order);

    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "productName", source = "product.productName")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderResponse> toListOrderResponse(List<Order> orders);

}
