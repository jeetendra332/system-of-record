
package com.example.sor.repository;

import com.example.sor.entity.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeHistoryRepository
        extends JpaRepository<EmployeeHistory, Long> {

    List<EmployeeHistory> findByEmployeeIdOrderByChangedAtDesc(
            Long employeeId);
}
