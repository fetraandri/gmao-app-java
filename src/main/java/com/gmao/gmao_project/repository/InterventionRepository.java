package com.gmao.gmao_project.repository;

import com.gmao.gmao_project.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
}