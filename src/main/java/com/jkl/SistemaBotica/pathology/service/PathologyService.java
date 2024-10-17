package com.jkl.SistemaBotica.pathology.service;

import com.jkl.SistemaBotica.Utils;
import com.jkl.SistemaBotica.pathology.entitie.Pathology;
import com.jkl.SistemaBotica.pathology.repository.PathologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathologyService {
    @Autowired
    private PathologyRepository pathologyRepository;

    public List<Pathology> getAll() {
        return pathologyRepository.findAll();
    }

    public Pathology getById(Long id) {
        return pathologyRepository.findById(id).orElse(null);
    }

    public Pathology save(Pathology pathology) {
        pathology.setIsDeleted(false);
        return pathologyRepository.save(pathology);
    }

    public Pathology update(Pathology pathology) {
        Pathology pathologyToUpdate = pathologyRepository.findById(pathology.getId()).orElse(null);

        Utils.updateField(pathology.getName(), pathologyToUpdate::setName);
        Utils.updateField(pathology.getDescription(), pathologyToUpdate::setDescription);
        Utils.updateField(pathology.getIsDeleted(), pathologyToUpdate::setIsDeleted);

        pathologyRepository.save(pathologyToUpdate);

        return pathologyToUpdate;
    }

    public boolean delete(Long id) {
        Pathology pathologyToDelete = pathologyRepository.findById(id).orElse(null);
        if (pathologyToDelete != null && !pathologyToDelete.getIsDeleted()) {
            pathologyToDelete.setIsDeleted(true);
            pathologyRepository.save(pathologyToDelete);
            return true;
        }
        return false;
    }
}
