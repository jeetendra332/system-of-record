package com.example.sor.controller;

import com.example.sor.entity.Department;
import com.example.sor.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @PostMapping
    public Department createDepartment(
            @RequestBody Department department) {

        return departmentRepository.save(department);
    }
}