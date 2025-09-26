package com.productmanagement.productmanagementapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
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

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("category is created")
                .isSuccess(true)
                .payload(categoryMapper.toCategoryResponse(category))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get a Category By Id")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable long id) {
        ApiResponse<Category> response = ApiResponse.<Category>builder()
                .code(HttpStatus.CREATED.value())
                .message("category is created")
                .isSuccess(true)
                .payload(categoryService.getById(id))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get All Categories")
    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        ApiResponse<?> response = ApiResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("all categories retrieved")
                .isSuccess(true)
                .payload(categoryService.allCategories())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Update a Category")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<?>> updateCategory(@PathVariable @Positive long id,
            @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryMapper.toEntity(categoryUpdateRequest);
        Category updatedCategory = categoryService.updateById(id, category);
        ApiResponse<?> response = ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("category updated successfully")
                .isSuccess(true)
                .payload(categoryMapper.toCategoryResponse(updatedCategory))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<?>> deleteCategory(@PathVariable @Positive long id) {
        categoryService.deleteById(id);
        ApiResponse<?> response = ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("category deleted successfully")
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add bulk of category")
    @PostMapping("/bulks")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> addBulk(
            @RequestBody @Valid List<CategoryDto> categoryDtos) {
        List<Category> categories = categoryMapper.toListCategoryEntity(categoryDtos);
        categoryService.addBulkCategory(categories);

        ApiResponse<List<CategoryResponse>> response = ApiResponse.<List<CategoryResponse>>builder()
                .code(HttpStatus.CREATED.value())
                .isSuccess(true)
                .message("bulk of category is created successfully")
                .payload(categoryMapper.toListCategoryResponse(categories))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(response);
    }
}
