package com.example.sor.repository;

import com.example.sor.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByEntityNameAndEntityIdOrderByCreatedAtDesc(
            String entityName, Long entityId);

    List<AuditLog> findAllByOrderByCreatedAtDesc();
}