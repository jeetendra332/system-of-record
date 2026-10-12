
package com.example.sor.controller;

import com.example.sor.dto.DashboardResponse;
import com.example.sor.repository.DepartmentRepository;
import com.example.sor.repository.EmployeeRepository;
import com.example.sor.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DashboardController(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public DashboardResponse getDashboard() {
        return new DashboardResponse(
                employeeRepository.count(),
                departmentRepository.count(),
                userRepository.count()
        );
    }
}
