package com.productmanagement.productmanagementapi.service.impl;

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
                .orElseThrow(() -> new NotFoundException("category id : " + id + "is not found"));
    }

    @Override
    public void deleteById(long id) {
        getById(id);
        categoryRepository.deleteById(id);
    }

}
