package com.productmanagement.productmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productmanagement.productmanagementapi.model.entity.OutOfInStockProduct;

@Repository
public interface OutOfInStockProductRepository extends JpaRepository<OutOfInStockProduct, Long> {

}
