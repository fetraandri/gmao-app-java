package com.gmao.gmao_project.service;

import com.gmao.gmao_project.model.Maintenance;
import com.gmao.gmao_project.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private EquipmentService equipmentService;

    public List<Maintenance> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        System.out.println("Maintenances retrieved: " + (maintenances != null ? maintenances.size() : "null"));
        return maintenances != null ? maintenances : Collections.emptyList();
    }

    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance avec l’ID " + id + " non trouvée"));
    }

    public void saveMaintenance(Maintenance maintenance) {
        // Vérifier que l’équipement existe
        equipmentService.getEquipmentById(maintenance.getEquipment().getId());
        maintenanceRepository.save(maintenance);
    }

    public void deleteMaintenance(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new EntityNotFoundException("Maintenance avec l’ID " + id + " non trouvée");
        }
        maintenanceRepository.deleteById(id);
    }
}