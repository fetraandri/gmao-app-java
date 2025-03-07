package com.gmao.gmao_project.service;

import com.gmao.gmao_project.model.Intervention;
import com.gmao.gmao_project.repository.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {

    @Autowired
    private InterventionRepository interventionRepository;

    public List<Intervention> getAllInterventions() {
        return interventionRepository.findAll();
    }

    public void saveIntervention(Intervention intervention) {
        interventionRepository.save(intervention);
    }
}