package com.gmao.gmao_project.controller;

import com.gmao.gmao_project.model.Equipment;
import com.gmao.gmao_project.model.Intervention;
import com.gmao.gmao_project.service.EquipmentService;
import com.gmao.gmao_project.service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private InterventionService interventionService;

    // Afficher la liste des équipements
    @GetMapping("/equipments")
    public ModelAndView getAllEquipments() {
        ModelAndView mav = new ModelAndView("equipment-list");
        mav.addObject("equipments", equipmentService.getAllEquipments());
        return mav;
    }

    // Afficher le formulaire pour ajouter un équipement
    @GetMapping("/equipments/new")
    public ModelAndView showEquipmentForm() {
        ModelAndView mav = new ModelAndView("equipment-form");
        mav.addObject("equipment", new Equipment());
        return mav;
    }

    // Sauvegarder un nouvel équipement
    @PostMapping("/equipments")
    public String saveEquipment(Equipment equipment) {
        equipmentService.saveEquipment(equipment);
        return "redirect:/equipments";
    }

    // Afficher le formulaire pour modifier un équipement
    @GetMapping("/equipments/edit/{id}")
    public ModelAndView showEditEquipmentForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("equipment-form");
        Equipment equipment = equipmentService.getEquipmentById(id);
        mav.addObject("equipment", equipment);
        return mav;
    }

    // Supprimer un équipement
    @GetMapping("/equipments/delete/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return "redirect:/equipments";
    }

    // Afficher la liste des interventions
    @GetMapping("/interventions")
    public ModelAndView getAllInterventions() {
        ModelAndView mav = new ModelAndView("intervention-list");
        List<Intervention> interventions = interventionService.getAllInterventions();
        System.out.println("Interventions retrieved: " + interventions.size());
        for (Intervention intervention : interventions) {
            System.out.println("Intervention ID: " + intervention.getId() + ", Equipment: " +
                    (intervention.getEquipment() != null ? intervention.getEquipment().getName() : "null"));
        }
        mav.addObject("interventions", interventions);
        return mav;
    }

    // Afficher le formulaire pour une nouvelle intervention
    @GetMapping("/interventions/new")
    public ModelAndView showInterventionForm() {
        ModelAndView mav = new ModelAndView("intervention-form-static"); // Garde le nom actuel
        mav.addObject("intervention", new Intervention());

        // Générer les options du <select> manuellement
        List<Equipment> equipments = equipmentService.getAllEquipments();
        System.out.println("Equipments retrieved: " + (equipments != null ? equipments.size() : "null"));
        StringBuilder equipmentOptions = new StringBuilder();
        equipmentOptions.append("<option value=\"\">-- Sélectionnez un équipement --</option>");
        if (equipments != null && !equipments.isEmpty()) {
            for (Equipment eq : equipments) {
                equipmentOptions.append("<option value=\"")
                        .append(eq.getId())
                        .append("\">")
                        .append(eq.getName())
                        .append("</option>");
            }
        } else {
            equipmentOptions.append("<option value=\"\" disabled>Aucun équipement disponible</option>");
        }
        String optionsHtml = equipmentOptions.toString();
        System.out.println("Generated equipmentOptions: " + optionsHtml); // Log pour vérification
        mav.addObject("equipmentOptions", optionsHtml); // Passer la chaîne HTML au modèle
        return mav;
    }

    // Sauvegarder une intervention
    @PostMapping("/interventions")
    public String saveIntervention(Intervention intervention) {
        interventionService.saveIntervention(intervention);
        return "redirect:/interventions";
    }
}