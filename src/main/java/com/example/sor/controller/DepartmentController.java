
package com.example.sor.controller;

import com.example.sor.entity.Department;
import com.example.sor.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Tag(
        name = "Departments",
        description = "APIs for managing departments"
)
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a department",
            description = "Creates a new department record"
    )
    public Department createDepartment(
            @RequestBody Department department) {
        return departmentService.create(department);
    }

    @GetMapping
    @Operation(
            summary = "Get all departments",
            description = "Retrieves all department records"
    )
    public List<Department> getAllDepartments() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get department by ID",
            description = "Retrieves a department using its unique ID"
    )
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a department",
            description = "Updates an existing department record"
    )
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a department",
            description = "Deletes a department using its unique ID"
    )
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
