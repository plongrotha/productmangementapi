package com.productmanagement.productmanagementapi.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
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

    private LocalDateTime outStockDate;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
