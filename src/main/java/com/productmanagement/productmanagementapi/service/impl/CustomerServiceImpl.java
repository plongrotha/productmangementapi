package com.productmanagement.productmanagementapi.service.impl;

import com.productmanagement.productmanagementapi.mapper.CustomerMapper;
import com.productmanagement.productmanagementapi.model.dto.CustomerDTO;
import com.productmanagement.productmanagementapi.model.entity.Customer;
import com.productmanagement.productmanagementapi.model.response.CustomerResponse;
import com.productmanagement.productmanagementapi.repository.CustomerRepository;
import com.productmanagement.productmanagementapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toCustomer(customerDTO);
        customer.setAge(Period.between(customerDTO.getDob(), LocalDate.now()).getYears());
        customer = customerRepository.save(customer);
       return customerMapper.toCustomerResponse(customer);
    }
}
