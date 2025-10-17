package com.productmanagement.productmanagementapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    @NotNull
    @JsonProperty("product_name")
    private String productName;


    @JsonProperty("description")
    private String productDescription;

    @Positive
    @JsonProperty("price")
    private BigDecimal productPrice;

    @Positive
    @JsonProperty("category_id")
    private Integer  categoryId;


    @JsonProperty("image")
    private String productImage;

    @Positive
    @JsonProperty("qty")
    private Integer quantity;

}
