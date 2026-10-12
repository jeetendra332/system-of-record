
package com.example.sor.service;

import com.example.sor.entity.Employee;
import com.example.sor.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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

    public List<Employee> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAll();
        }

        String searchText = keyword.trim().toLowerCase(Locale.ROOT);

        return employeeRepository.findAll()
                .stream()
                .filter(employee ->
                        contains(employee.getEmployeeCode(), searchText)
                                || contains(employee.getFirstName(), searchText)
                                || contains(employee.getLastName(), searchText)
                                || contains(employee.getEmail(), searchText)
                                || contains(employee.getPhone(), searchText)
                                || contains(employee.getDesignation(), searchText)
                )
                .toList();
    }

    private boolean contains(String value, String searchText) {
        return value != null
                && value.toLowerCase(Locale.ROOT).contains(searchText);
    }
}
