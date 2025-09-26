package com.productmanagement.productmanagementapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.productmanagement.productmanagementapi.model.dto.ProductRequest;
import com.productmanagement.productmanagementapi.model.entity.Product;
import com.productmanagement.productmanagementapi.model.response.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "inStock", ignore = true)
    Product toEntity(ProductRequest dto);

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "categoryName", source = "category.categoryName")
    ProductResponse toDto(Product entity);

    List<ProductResponse> toDto(List<Product> entities);

    List<Product> toEntity(List<ProductRequest> dtos);

}
