package com.productmanagement.productmanagementapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.productmanagementapi.mapper.UserMapper;
import com.productmanagement.productmanagementapi.model.dto.UserDTO;
import com.productmanagement.productmanagementapi.model.dto.UserUpdateDto;
import com.productmanagement.productmanagementapi.model.entity.User;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.UserResponse;
import com.productmanagement.productmanagementapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    public final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Create a User")
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserDTO dto) {

        User user = userMapper.toUserEntity(dto);
        userService.createUser(user);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("user created successfully")
                .isSuccess(true)
                .payload(userMapper.toUserResponse(user))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get All Email")
    @GetMapping("/email")
    public ResponseEntity<ApiResponse<List<String>>> getAllEmail() {

        List<String> list = userService.getAllEmails();
        ApiResponse<List<String>> response = ApiResponse.<List<String>>builder()
                .code(HttpStatus.CREATED.value())
                .message("all email get successfully")
                .isSuccess(true)
                .payload(list)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Add Bulk Users")
    @PostMapping("/bulks")
    public ResponseEntity<ApiResponse<List<UserResponse>>> addBulkUsers(@RequestBody @Valid List<UserDTO> dtos) {

        List<User> users = userMapper.toListUserEntity(dtos);
        userService.createUsersBulk(users);

        ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.CREATED.value())
                .message("add users bulk is successfully")
                .isSuccess(true)
                .payload(userMapper.toListUserResponse(users))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Update user By Id")
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse<UserResponse>> updateUserById(
            @PathVariable @Positive long id,
            @RequestBody @Valid UserUpdateDto dto) {
        User user = userMapper.toUserEntity(dto);
        userService.updateUserById(id, user);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("user is updated successfully")
                .isSuccess(true)
                .payload(userMapper.toUserResponse(user))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get All Users")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("all user get successfully")
                .isSuccess(true)
                .payload(userMapper.toListUserResponse(users))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete a User by Id")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable @Positive long id) {
        userService.deleteUserById(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("user is deleted")
                .isSuccess(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get an User By Id")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable @Positive long id) {
        User user = userService.getUserById(id);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("user retrieve successfully")
                .isSuccess(true)
                .payload(userMapper.toUserResponse(user))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(response);
    }
}
