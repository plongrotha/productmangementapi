package com.productmanagement.productmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productmanagement.productmanagementapi.model.entity.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepsitory extends JpaRepository<OrderItem, Long> {


}
