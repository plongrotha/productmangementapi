package com.productmanagement.productmanagementapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.exception.ResourceAlreadyExistException;
import com.productmanagement.productmanagementapi.model.entity.Category;
import com.productmanagement.productmanagementapi.repository.CategoryRepository;
import com.productmanagement.productmanagementapi.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCreateCategory(Category category) {
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new ResourceAlreadyExistException("Category is already exist");
        }
        categoryRepository.save(category);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category id : " + id + " is not found"));
    }

    @Override
    public void deleteById(long id) {
        getById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> allCategories() {
        return Optional.of(categoryRepository.findAll()).orElseThrow(() -> new NotFoundException("no found category"));
    }

    @Override
    public Category updateById(long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category id : " + id + " is not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setUpdateAt(LocalDateTime.now());
        return categoryRepository.save(existingCategory);
    }

}
