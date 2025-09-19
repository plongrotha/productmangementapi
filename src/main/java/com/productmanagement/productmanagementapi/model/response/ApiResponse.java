package com.productmanagement.productmanagementapi.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int code;
    private String message;
    private boolean isSuccess;
    private T payload;
    private LocalDateTime timestamp;
}
