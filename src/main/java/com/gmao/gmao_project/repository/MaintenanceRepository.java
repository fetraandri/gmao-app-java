package com.gmao.gmao_project.repository;

import com.gmao.gmao_project.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}