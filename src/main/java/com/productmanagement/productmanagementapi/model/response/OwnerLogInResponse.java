package com.productmanagement.productmanagementapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerLogInResponse {
    private Long id;
    private String username;
    private String phoneNumber;
}
