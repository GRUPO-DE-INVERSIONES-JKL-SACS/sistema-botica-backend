package com.jkl.SistemaBotica.laboratory.service;

import com.jkl.SistemaBotica.Utils;
import com.jkl.SistemaBotica.laboratory.entitie.Laboratory;
import com.jkl.SistemaBotica.laboratory.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryService {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public List<Laboratory> getAll() {
        return laboratoryRepository.findAll();
    }

    public Laboratory getById(Long id) {
        return laboratoryRepository.findById(id).orElse(null);
    }

    public Laboratory save(Laboratory laboratory) {
        laboratory.setIsDeleted(false);
        return laboratoryRepository.save(laboratory);
    }

    public Laboratory update(Laboratory laboratory) {
        Laboratory laboratoryToUpdate = laboratoryRepository.findById(laboratory.getId()).orElse(null);

        Utils.updateField(laboratory.getName(), laboratoryToUpdate::setName);
        Utils.updateField(laboratory.getDescription(), laboratoryToUpdate::setDescription);
        Utils.updateField(laboratory.getIsDeleted(), laboratoryToUpdate::setIsDeleted);

        laboratoryRepository.save(laboratoryToUpdate);

        return laboratoryToUpdate;
    }

    public boolean delete(Long id) {
        Laboratory laboratoryToDelete = laboratoryRepository.findById(id).orElse(null);
        if (laboratoryToDelete != null && !laboratoryToDelete.getIsDeleted()) {
            laboratoryToDelete.setIsDeleted(true);
            laboratoryRepository.save(laboratoryToDelete);
            return true;
        }
        return false;
    }
}