package com.productmanagement.productmanagementapi.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    @JsonProperty("category_id")
    private long id;
    @JsonProperty("category_name")
    private String cateName;

    @JsonIgnore
    private String description;

    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createAt;

    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updateAt;
}
