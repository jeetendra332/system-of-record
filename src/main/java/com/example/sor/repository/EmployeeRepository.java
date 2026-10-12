
package com.example.sor.repository;

import com.example.sor.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    // Find employee by unique email
    Optional<Employee> findByEmail(String email);

    // Check whether email already exists
    boolean existsByEmail(String email);

    // Find employee by employee code
    Optional<Employee> findByEmployeeCode(String employeeCode);

    // Check whether employee code already exists
    boolean existsByEmployeeCode(String employeeCode);

    // Search employees by first name, ignoring case
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
}
