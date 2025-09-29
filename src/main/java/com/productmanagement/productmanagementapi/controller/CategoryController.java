package com.productmanagement.productmanagementapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.productmanagementapi.mapper.CategoryMapper;
import com.productmanagement.productmanagementapi.model.dto.CategoryDto;
import com.productmanagement.productmanagementapi.model.dto.CategoryUpdateRequest;
import com.productmanagement.productmanagementapi.model.entity.Category;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.CategoryResponse;
import com.productmanagement.productmanagementapi.service.CategoryService;
import com.productmanagement.productmanagementapi.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
@Tag(name = "Category Management", description = "APIs for managing categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Create a Category")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        categoryService.createCreateCategory(category);
        return ResponseUtil.created("category is created successfully", categoryMapper.toCategoryResponse(category));
    }

    @Operation(summary = "Get a Category By Id")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable @Positive long id) {
        return ResponseUtil.ok("category retrieved successfully", categoryService.getById(id));
    }

    @Operation(summary = "Get All Categories")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategory() {
        List<Category> categories = categoryService.allCategories();
        return ResponseUtil.ok("all categories retrieved successfully",
                categoryMapper.toListCategoryResponse(categories));
    }

    @Operation(summary = "Update a Category")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable @Positive long id,
            @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryMapper.toEntity(categoryUpdateRequest);
        Category updatedCategory = categoryService.updateById(id, category);
        return ResponseUtil.ok("category updated successfully", categoryMapper.toCategoryResponse(updatedCategory));
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable @Positive long id) {
        categoryService.deleteById(id);
        return ResponseUtil.ok("category deleted successfully");
    }

    @Operation(summary = "Add bulk of category")
    @PostMapping("/bulks")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> addBulk(
            @RequestBody @Valid List<CategoryDto> categoryDtos) {
        List<Category> categories = categoryMapper.toListCategoryEntity(categoryDtos);
        List<Category> savedCategories = categoryService.addBulkCategory(categories);
        return ResponseUtil.created("all categories created successfully",
                categoryMapper.toListCategoryResponse(savedCategories));
    }
}
