package com.productmanagement.productmanagementapi.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.productmanagement.productmanagementapi.exception.InvalidException;
import com.productmanagement.productmanagementapi.exception.NotFoundException;
import com.productmanagement.productmanagementapi.exception.ResourceAlreadyExistException;
import com.productmanagement.productmanagementapi.model.entity.Employee;
import com.productmanagement.productmanagementapi.repository.EmployeeRepository;
import com.productmanagement.productmanagementapi.service.EmployeeService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        Random random = new Random();
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ResourceAlreadyExistException("email : " + employee.getEmail() + "already existed.");
        }
        employee.setFullName(employee.getFirstName() + " " + employee.getLastName());
        employee.setUserName((employee.getFirstName() + employee.getLastName() + random.nextInt(100)).toLowerCase());
        employee.setPassword(generateRandomPassword(random));
        if (employee.getDateOfBirth() != null) {
            employee.setAge(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears());
        } else {
            throw new InvalidException("date of birth cannot be null");
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public List<Employee> createEmployeesBulk(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new NotFoundException("no employee to insert");
        }
        for (Employee employee : employees) {
            if (StringUtils.isNotEmpty(employee.getFirstName()) && StringUtils.isNotEmpty(employee.getLastName())) {
                employee.setFirstName(employee.getFirstName());
                employee.setLastName(employee.getLastName());
                employee.setFullName(employee.getFirstName() + " " + employee.getLastName());
                employee.setUserName(employee.getFirstName() + employee.getLastName() + new Random().nextInt(100));
            }
            if (employee.getDateOfBirth() != null) {
                employee.setAge(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears());
            }
            if (StringUtils.isNotEmpty(employee.getPassword())) {
                employee.setPassword(generateRandomPassword(new Random()));
            }
        }
        return employeeRepository.saveAll(employees);
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("employee not found with id : " + id));
    }

    @Override
    public Employee updateEmployeeById(long id, Employee employee) {
        Employee existed = getEmployeeById(id);
        existed.setFirstName(employee.getFirstName());
        existed.setLastName(employee.getLastName());
        existed.setFullName(existed.getFirstName() + " " + existed.getLastName());
        existed.setEmail(employee.getEmail());
        existed.setPhoneNumber(employee.getPhoneNumber());
        existed.setDateOfBirth(employee.getDateOfBirth());
        existed.setAge(Period.between(existed.getDateOfBirth(), LocalDate.now()).getYears());
        return employeeRepository.save(existed);
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        log.info("employees size : {}", employees.size());

        if (employees.isEmpty()) {
            throw new NotFoundException("no employee found");
        }
        return employees;
    }

    @Override
    public void deleteEmployeeById(long id) {
        getEmployeeById(id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<String> getAllEmails() {
        return employeeRepository.findAllEmail().orElseThrow(() -> new NotFoundException("email is not found"));
    }

    // generate password
    private String generateRandomPassword(Random random) {
        String chars = "0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    // @PostConstruct
    @SuppressWarnings("unused")
    private void addEmployee() {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) { // Generate 50 fake employees
            Employee employee = new Employee();
            Random random = new Random();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setFullName(firstName + " " + lastName);
            employee.setUserName((firstName + lastName + random.nextInt(1000)).toLowerCase());
            employee.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() +
                    random.nextInt(100) + "@" + faker.internet().domainName());
            employee.setPassword(faker.internet().password(4, 6, true, true));
            employee.setPhoneNumber(faker.number().digits(random.nextInt(8) + 8));
            LocalDate birthDate = LocalDate.now()
                    .minusYears(18 + random.nextInt(62))
                    .minusDays(random.nextInt(365));
            employee.setDateOfBirth(birthDate);
            employee.setAge(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears());
            employeeRepository.save(employee);
        }
    }

}