
package com.example.sor.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.example.sor.dto.EmployeeRequest;
import com.example.sor.dto.EmployeeResponse;
import com.example.sor.dto.EmployeeResponseMapper;
import com.example.sor.entity.Department;
import com.example.sor.entity.Employee;
import com.example.sor.repository.DepartmentRepository;
import com.example.sor.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(
        name = "Employees",
        description = "APIs for managing employee records"
)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    public EmployeeController(
            EmployeeService employeeService,
            DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create an employee",
            description = "Creates a new employee record associated with a department"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "409", description = "Employee data conflicts with existing data")
    })
    public EmployeeResponse createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = convertRequestToEmployee(request);
        Employee savedEmployee = employeeService.create(employee);

        return EmployeeResponseMapper.toResponse(savedEmployee);
    }

    @GetMapping
    @Operation(
            summary = "Get all employees",
            description = "Retrieves all employee records"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employees retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions"
            )
    })
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAll()
                .stream()
                .map(EmployeeResponseMapper::toResponse)
                .toList();
    }



    @GetMapping("/{id}")
    @Operation(
            summary = "Get employee by ID",
            description = "Retrieves an employee using their unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found"
            )
    })
    public EmployeeResponse getEmployeeById(
            @PathVariable Long id) {

        Employee employee = employeeService.getById(id);

        return EmployeeResponseMapper.toResponse(employee);
    }



    @PutMapping("/{id}")
    @Operation(
            summary = "Update an employee",
            description = "Updates an existing employee record"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid employee data"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee or department not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict with existing data"
            )
    })
    public EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = convertRequestToEmployee(request);

        Employee updatedEmployee =
                employeeService.update(id, employee);

        return EmployeeResponseMapper.toResponse(updatedEmployee);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete an employee",
            description = "Deletes an employee using their unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Employee deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found"
            )
    })
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }



    @GetMapping("/search")
    @Operation(
            summary = "Search employees by name",
            description = "Searches employee records using a name query parameter"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Search completed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Missing or invalid search parameter"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    public List<EmployeeResponse> searchEmployees(
            @RequestParam String name) {

        return employeeService.search(name)
                .stream()
                .map(EmployeeResponseMapper::toResponse)
                .toList();
    }


    private Employee convertRequestToEmployee(
            EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDesignation(request.getDesignation());

        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found"
                ));

        employee.setDepartment(department);

        return employee;
    }
}
