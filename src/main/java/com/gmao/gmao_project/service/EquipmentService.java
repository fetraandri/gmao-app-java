package com.gmao.gmao_project.service;

import com.gmao.gmao_project.model.Equipment;
import com.gmao.gmao_project.repository.EquipmentRepository;
import com.gmao.gmao_project.repository.InterventionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private InterventionRepository interventionRepository;

    public List<Equipment> getAllEquipments() {
        List<Equipment> equipments = equipmentRepository.findAll();
        System.out.println("Equipments retrieved: " + (equipments != null ? equipments.size() : "null"));
        return equipments != null ? equipments : Collections.emptyList();
    }

    public void saveEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé avec l'ID : " + id));
    }

    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Équipement avec l’ID " + id + " non trouvé");
        }
        // Supprimer toutes les interventions liées à cet équipement
        interventionRepository.deleteByEquipmentId(id);
        equipmentRepository.deleteById(id);
    }
}