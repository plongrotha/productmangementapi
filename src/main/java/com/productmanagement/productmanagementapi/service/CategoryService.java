package com.productmanagement.productmanagementapi.service;

import com.productmanagement.productmanagementapi.model.entity.Category;

public interface CategoryService {

    void createCreateCategory(Category category);

    Category getById(long id);

    void deleteById(long id);

}
