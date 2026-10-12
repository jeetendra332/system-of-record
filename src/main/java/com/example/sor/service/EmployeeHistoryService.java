
package com.example.sor.service;

import com.example.sor.entity.EmployeeHistory;
import com.example.sor.repository.EmployeeHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeHistoryService {

    private final EmployeeHistoryRepository historyRepository;

    public EmployeeHistoryService(
            EmployeeHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public EmployeeHistory recordChange(
            Long employeeId,
            String fieldChanged,
            String oldValue,
            String newValue) {

        EmployeeHistory history = new EmployeeHistory();

        history.setEmployeeId(employeeId);
        history.setFieldChanged(fieldChanged);
        history.setOldValue(oldValue);
        history.setNewValue(newValue);

        return historyRepository.save(history);
    }

    public List<EmployeeHistory> getHistory(Long employeeId) {
        return historyRepository
                .findByEmployeeIdOrderByChangedAtDesc(employeeId);
    }
}
