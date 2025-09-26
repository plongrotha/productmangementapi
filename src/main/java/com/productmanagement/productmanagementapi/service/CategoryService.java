package com.productmanagement.productmanagementapi.service;

import java.util.List;

import com.productmanagement.productmanagementapi.model.entity.Category;

public interface CategoryService {

    void createCreateCategory(Category category);

    Category getById(long id);

    void deleteById(long id);

    List<Category> allCategories();

    List<Category> addBulkCategory(List<Category> categories);

    Category updateById(long id, Category category);

}
