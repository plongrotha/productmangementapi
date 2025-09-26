package com.productmanagement.productmanagementapi.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull
    private String productName;

    @Positive
    @NotNull
    private int quantity;
    private String imageUrl;
    @Positive
    private BigDecimal price;

    @NotNull
    private long categoryId;

}
