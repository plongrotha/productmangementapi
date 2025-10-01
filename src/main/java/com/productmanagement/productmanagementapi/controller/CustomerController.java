package com.productmanagement.productmanagementapi.controller;

import com.productmanagement.productmanagementapi.mapper.CustomerMapper;
import com.productmanagement.productmanagementapi.model.dto.CustomerDTO;
import com.productmanagement.productmanagementapi.model.entity.Customer;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.CustomerResponse;
import com.productmanagement.productmanagementapi.service.CustomerService;
import com.productmanagement.productmanagementapi.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;


    @Operation(summary = "Create a Customer")
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerDTO customer) {
        CustomerResponse response = customerService.createCustomer(customer);
        return ResponseUtil.ok("create customer success", response);
    }

    @Operation(summary = "Get a Customer by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable @Positive Long id) {
        return ResponseUtil.ok("get customer success", customerService.getCustomerById(id));
    }

    @Operation(summary = "Get All Customers")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseUtil.ok("all customers retrieve successfully", customerMapper.toCustomerResponseList(customers));
    }

    @Operation(summary = "Delete a Customer By Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable @Positive Long id) {
        return ResponseUtil.ok("delete customer success");
    }
}