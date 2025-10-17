package com.productmanagement.productmanagementapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.productmanagement.productmanagementapi.model.dto.CategoryDto;
import com.productmanagement.productmanagementapi.model.dto.CategoryUpdateRequest;
import com.productmanagement.productmanagementapi.model.entity.Category;
import com.productmanagement.productmanagementapi.model.response.CategoryResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "categoryId", ignore = true),
            @Mapping(target = "products", ignore = true),
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "updateAt", ignore = true)
    })
    Category toEntity(CategoryDto categoryDto);

    @Mapping(target = "cateName", source = "categoryName")
    @Mapping(target = "id", source = "categoryId")
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Category toEntity(CategoryUpdateRequest categoryUpdateRequest);

    List<CategoryResponse> toListCategoryResponse(List<Category> categories);

    List<Category> toListCategoryEntity(List<CategoryDto> categoryDtos);

}
