package com.gmao.gmao_project.controller;

import com.gmao.gmao_project.model.Maintenance;
import com.gmao.gmao_project.service.MaintenanceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/maintenances")
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/maintenances/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        try {
            Maintenance maintenance = maintenanceService.getMaintenanceById(id);
            return ResponseEntity.ok(maintenance);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/maintenances")
    public ResponseEntity<Maintenance> saveMaintenance(@RequestBody Maintenance maintenance) {
        try {
            maintenanceService.saveMaintenance(maintenance);
            return ResponseEntity.ok(maintenance);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build(); // 400 si l’équipement n’existe pas
        }
    }

    @DeleteMapping("/maintenances/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        try {
            maintenanceService.deleteMaintenance(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}