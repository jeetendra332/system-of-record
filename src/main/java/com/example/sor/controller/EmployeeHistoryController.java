
package com.example.sor.controller;

import com.example.sor.entity.EmployeeHistory;
import com.example.sor.service.EmployeeHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeHistoryController {

    private final EmployeeHistoryService historyService;

    public EmployeeHistoryController(
            EmployeeHistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/{employeeId}/history")
    public List<EmployeeHistory> getEmployeeHistory(
            @PathVariable Long employeeId) {
        return historyService.getHistory(employeeId);
    }
}
