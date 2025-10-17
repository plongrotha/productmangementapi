package com.productmanagement.productmanagementapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.productmanagement.productmanagementapi.mapper.EmployeeMapper;
import com.productmanagement.productmanagementapi.model.dto.EmployeeDTO;
import com.productmanagement.productmanagementapi.model.dto.EmployeeUpdateDto;
import com.productmanagement.productmanagementapi.model.entity.Employee;
import com.productmanagement.productmanagementapi.model.response.ApiResponse;
import com.productmanagement.productmanagementapi.model.response.EmployeeResponse;
import com.productmanagement.productmanagementapi.service.EmployeeService;
import com.productmanagement.productmanagementapi.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
@Tag(name = "Employee Management", description = "APIs for managing employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Operation(summary = "Create an Employee")
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@RequestBody @Valid EmployeeDTO dto) {
        Employee employee = employeeMapper.toEmployeeEntity(dto);
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseUtil.created("employee", employeeMapper.toEmployeeResponse(savedEmployee));
    }

    @Operation(summary = "Get All Email")
    @GetMapping("/email")
    public ResponseEntity<ApiResponse<List<String>>> getAllEmail() {
        List<String> list = employeeService.getAllEmails();
        return ResponseUtil.ok("all email get successfully", list);
    }

    @Operation(summary = "Update Employee By Id")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployeeById(
            @PathVariable @Positive Long id,
            @RequestBody @Valid EmployeeUpdateDto dto) {
        Employee employee = employeeMapper.toEmployeeEntity(dto);
        Employee updatedEmployee = employeeService.updateEmployeeById(id, employee);
        return ResponseUtil.ok("employee updated successfully", employeeMapper.toEmployeeResponse(updatedEmployee));
    }

    @Operation(summary = "Get All Employees")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseUtil.ok("all employees retrieve successfully", employeeMapper.toListEmployeeResponse(employees));
    }

    @Operation(summary = "Delete an Employee by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable @Positive Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseUtil.ok("employee deleted successfully");
    }

    @Operation(summary = "Get an Employee By Id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable @Positive Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseUtil.ok("employee retrieve successfully", employeeMapper.toEmployeeResponse(employee));
    }

    @Operation(summary = "Create Bulk Employees")
    @PostMapping("/bulks")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> createEmployeeBulks(@RequestBody List<EmployeeDTO> employeeDTOS) {
        List<Employee> employees = employeeMapper.toListEmployeeEntity(employeeDTOS);
        List<Employee> savedAllEmployees = employeeService.createEmployeesBulk(employees);
        return ResponseUtil.created("all employees bulk successfully", employeeMapper.toListEmployeeResponse(savedAllEmployees));
    }
}