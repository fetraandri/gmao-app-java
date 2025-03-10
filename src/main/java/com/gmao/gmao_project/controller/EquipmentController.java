package com.gmao.gmao_project.controller;

import com.gmao.gmao_project.model.Equipment;
import com.gmao.gmao_project.model.Intervention;
import com.gmao.gmao_project.service.EquipmentService;
import com.gmao.gmao_project.service.InterventionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Remplace @Controller pour retourner du JSON
@RequestMapping("/api") // Préfixe tous les endpoints avec /api
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private InterventionService interventionService;

    // Afficher la liste des équipements (GET /api/equipments)
    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        System.out.println("Equipments retrieved: " + (equipments != null ? equipments.size() : "null"));
        return ResponseEntity.ok(equipments);
    }

    // Afficher un équipement spécifique pour édition (GET /api/equipments/{id})
    @GetMapping("/equipments/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        return ResponseEntity.ok(equipment);
    }

    // Ajouter ou modifier un équipement (POST /api/equipments)
    @PostMapping("/equipments")
    public ResponseEntity<Equipment> saveEquipment(@RequestBody Equipment equipment) {
        equipmentService.saveEquipment(equipment);
        return ResponseEntity.ok(equipment);
    }

    // Modifier un équipement (PUT /api/equipments/{id})
    @PutMapping("/equipments/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        equipment.setId(id); // Assure que l'ID correspond
        equipmentService.saveEquipment(equipment);
        return ResponseEntity.ok(equipment);
    }

    // Supprimer un équipement (DELETE /api/equipments/{id})
    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }
    // Afficher la liste des interventions (GET /api/interventions)
    @GetMapping("/interventions")
    public ResponseEntity<List<Intervention>> getAllInterventions() {
        List<Intervention> interventions = interventionService.getAllInterventions();
        System.out.println("Interventions retrieved: " + interventions.size());
        for (Intervention intervention : interventions) {
            System.out.println("Intervention ID: " + intervention.getId() + ", Equipment: " +
                    (intervention.getEquipment() != null ? intervention.getEquipment().getName() : "null"));
        }
        return ResponseEntity.ok(interventions);
    }

    // Fournir les équipements pour le formulaire d’intervention (GET /api/equipments/for-intervention)
    @GetMapping("/equipments/for-intervention")
    public ResponseEntity<List<Equipment>> getEquipmentsForIntervention() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        System.out.println("Equipments retrieved for intervention: " + (equipments != null ? equipments.size() : "null"));
        return ResponseEntity.ok(equipments);
    }

    // Sauvegarder une intervention (POST /api/interventions)
    @PostMapping("/interventions")
    public ResponseEntity<Intervention> saveIntervention(@RequestBody Intervention intervention) {
        interventionService.saveIntervention(intervention);
        return ResponseEntity.ok(intervention);
    }
}