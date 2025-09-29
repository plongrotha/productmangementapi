package com.productmanagement.productmanagementapi.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.productmanagement.productmanagementapi.model.response.ApiResponse;

public class ResponseUtil {

    public ResponseUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(HttpStatus status, String message,
            T payload) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .code(status.value())
                .message(message)
                .isSuccess(true)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(HttpStatus status, String message) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .code(status.value())
                .message(message)
                .isSuccess(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    public static ResponseEntity<ApiResponse<Void>> error(String message, String errorCode, HttpStatus status) {
        return ResponseEntity.status(status).body(
                ApiResponse.<Void>builder()
                        .isSuccess(false)
                        .message(message)
                        .errorCode(errorCode)
                        .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T payload) {
        return createSuccessResponse(HttpStatus.OK, message, payload);
    }

    public static ResponseEntity<ApiResponse<Void>> ok(String message) {
        return createSuccessResponse(HttpStatus.OK, message);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T payload) {
        return createSuccessResponse(HttpStatus.CREATED, message, payload);
    }

}
