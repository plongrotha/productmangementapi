package com.productmanagement.productmanagementapi.mapper;

import com.productmanagement.productmanagementapi.model.dto.CustomerDTO;
import com.productmanagement.productmanagementapi.model.entity.Customer;
import com.productmanagement.productmanagementapi.model.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {



    @Mapping(target = "id", source = "customerId")
    @Mapping(target = "phoneNumber", source = "phone")
    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "phone", source = "phoneNumber")
    Customer toCustomer(CustomerDTO customerDTO);

    List<CustomerResponse> toCustomerResponseList(List<Customer> customerList);



}
