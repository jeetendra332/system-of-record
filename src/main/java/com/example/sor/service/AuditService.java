package com.example.sor.service;

import com.example.sor.entity.AuditLog;
import com.example.sor.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional
    public AuditLog log(
            String performedBy,
            String action,
            String entityName,
            Long entityId,
            String details) {

        AuditLog auditLog = new AuditLog();
        auditLog.setPerformedBy(performedBy);
        auditLog.setAction(action);
        auditLog.setEntityName(entityName);
        auditLog.setEntityId(entityId);
        auditLog.setDetails(details);

        return auditLogRepository.save(auditLog);
    }

    @Transactional(readOnly = true)
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public List<AuditLog> getLogsForEntity(
            String entityName, Long entityId) {

        return auditLogRepository
                .findByEntityNameAndEntityIdOrderByCreatedAtDesc(
                        entityName, entityId);
    }
}