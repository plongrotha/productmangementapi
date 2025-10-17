package com.productmanagement.productmanagementapi.service;

import com.productmanagement.productmanagementapi.model.dto.CustomerDTO;
import com.productmanagement.productmanagementapi.model.entity.Customer;
import com.productmanagement.productmanagementapi.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerDTO customerDTO);

    void deleteCustomerById(Long id);

    List<Customer> getAllCustomers();

    CustomerResponse getCustomerById(Long id);


}
