package com.example.sor.service;

import com.example.sor.entity.Employee;
import com.example.sor.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
    }

    public Employee update(Long id, Employee details) {

        Employee employee = getById(id);

        employee.setEmployeeCode(details.getEmployeeCode());
        employee.setFirstName(details.getFirstName());
        employee.setLastName(details.getLastName());
        employee.setEmail(details.getEmail());
        employee.setPhone(details.getPhone());
        employee.setDesignation(details.getDesignation());
        employee.setDepartment(details.getDepartment());

        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
    }
}