package com.productmanagement.productmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productmanagement.productmanagementapi.model.entity.OrderItem;

@Repository
public interface OrderItemRepsitory extends JpaRepository<OrderItem, Long> {

    void deleteByProduct_productId(long productId);

}
