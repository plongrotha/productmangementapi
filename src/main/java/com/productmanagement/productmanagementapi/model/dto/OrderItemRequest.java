package com.productmanagement.productmanagementapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    @NotNull
    @Positive
    @Schema(example = "1")
    private Long productId;

    @NotNull
    @Positive
    @Schema(example = "2")
    private Integer quantity;
}
