package com.productmanagement.productmanagementapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private long id;
    private String productName;
    private int quantity;
    private String imageUrl;
    private boolean inStock;
    private String price;
    private Integer categoryId;
}
