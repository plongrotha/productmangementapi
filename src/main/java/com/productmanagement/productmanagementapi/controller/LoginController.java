package com.productmanagement.productmanagementapi.controller;

import com.productmanagement.productmanagementapi.mapper.OwnerMapper;
import com.productmanagement.productmanagementapi.model.dto.OwnerRequest;
import com.productmanagement.productmanagementapi.model.entity.Owner;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.OwnerLogInResponse;
import com.productmanagement.productmanagementapi.service.OwnerService;
import com.productmanagement.productmanagementapi.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<OwnerLogInResponse>> login(@RequestBody @Valid OwnerRequest ownerRequest) {
        Owner owner = ownerMapper.toOwner(ownerRequest);
        return ResponseUtil.ok("login successfully", ownerMapper.toOwnerLogInResponse(owner));
    }

}
