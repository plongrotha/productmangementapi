package com.productmanagement.productmanagementapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
public class OwnerRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
