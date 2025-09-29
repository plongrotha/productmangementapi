package com.productmanagement.productmanagementapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.productmanagement.productmanagementapi.model.dto.EmployeeDTO;
import com.productmanagement.productmanagementapi.model.dto.EmployeeUpdateDto;
import com.productmanagement.productmanagementapi.model.entity.Employee;
import com.productmanagement.productmanagementapi.model.response.EmployeeResponse;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "dateOfBirth", source = "dob")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    Employee toEmployeeEntity(EmployeeDTO dto);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "dateOfBirth", source = "dob")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userName", ignore = true)
    Employee toEmployeeEntity(EmployeeUpdateDto dto);

    @Mapping(target = "dob", source = "dateOfBirth")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "dob", source = "dateOfBirth")
    @Mapping(target = "id", source = "employeeId")
    EmployeeResponse toEmployeeResponse(Employee employee);

    List<Employee> toListEmployeeEntity(List<EmployeeDTO> dtos);

    List<EmployeeResponse> toListEmployeeResponse(List<Employee> list);

}