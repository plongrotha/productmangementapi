package com.productmanagement.productmanagementapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private int orderItemId;
    private int productId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;

}
