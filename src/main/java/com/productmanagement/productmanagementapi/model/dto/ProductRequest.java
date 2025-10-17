package com.productmanagement.productmanagementapi.model.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Product name is required and cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Schema(description = "Name of the product", example = "iPhone 15 Pro", required = true)
    private String productName;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Schema(description = "Product description", example = "Latest iPhone with advanced features")
    private String description;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Schema(description = "Available quantity of the product", example = "100", required = true)
    private Integer quantity;

    // @Pattern(regexp = "^(https?://).*\\.(jpg|jpeg|png|gif|bmp|webp)$", message =
    // "Image URL must be a valid HTTP/HTTPS URL ending with a valid image
    // extension")
    @Schema(description = "URL of the product image", example = "https://i.pinimg.com/1200x/1a/67/f1/1a67f1c9d6ba63c919a30ed9f08229c6.jpg")
    private String imageUrl;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 integer digits and 2 decimal places")
    @Schema(description = "Price of the product", example = "999.99", required = true)
    private BigDecimal price;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be a positive number")
    @Schema(description = "ID of the product category", example = "1", required = true)
    private Long categoryId;

}
