package com.example.sor.controller;

import com.example.sor.entity.AuditLog;
import com.example.sor.service.AuditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditService.getAllLogs();
    }

    @GetMapping("/{entityName}/{entityId}")
    public List<AuditLog> getLogsForEntity(
            @PathVariable String entityName,
            @PathVariable Long entityId) {

        return auditService.getLogsForEntity(entityName, entityId);
    }
}