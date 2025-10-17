package com.productmanagement.productmanagementapi.mapper;

import com.productmanagement.productmanagementapi.model.entity.OrderItem;
import com.productmanagement.productmanagementapi.model.response.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

}
