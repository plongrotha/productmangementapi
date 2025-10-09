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
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Customer toCustomer(CustomerDTO customerDTO);

    List<CustomerResponse> toCustomerResponseList(List<Customer> customerList);

}
