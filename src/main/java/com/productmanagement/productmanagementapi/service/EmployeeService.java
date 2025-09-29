package com.productmanagement.productmanagementapi.service;

import java.util.List;

import com.productmanagement.productmanagementapi.model.entity.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<String> getAllEmails();

    Employee getEmployeeById(long id);

    Employee updateEmployeeById(long id, Employee employee);

    List<Employee> getAllEmployees();

    void deleteEmployeeById(long id);

    List<Employee> createEmployeesBulk(List<Employee> employees);

}