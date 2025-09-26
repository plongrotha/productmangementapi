package com.productmanagement.productmanagementapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {

    @NotEmpty(message = "categoryName cannot be empty")
    @JsonProperty("categoryname")
    private String categoryName;

    @NotEmpty(message = "categoryName cannot be empty")
    private String description;
}
