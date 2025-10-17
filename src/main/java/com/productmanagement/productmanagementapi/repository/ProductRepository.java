package com.productmanagement.productmanagementapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productmanagement.productmanagementapi.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductName(String name);

    List<Product> findByCategory_CategoryId(long categoryId);

    List<Product> findByIsInStockTrue();

}