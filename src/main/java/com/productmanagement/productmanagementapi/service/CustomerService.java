package com.productmanagement.productmanagementapi.service;

import com.productmanagement.productmanagementapi.model.dto.CustomerDTO;
import com.productmanagement.productmanagementapi.model.response.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerDTO customerDTO);

}
