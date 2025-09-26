package com.productmanagement.productmanagementapi.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "out_of_in_stock_products")
public class OutOfInStockProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long outInStockId;

    @CreationTimestamp
    private LocalDateTime outStockDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
