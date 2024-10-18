package com.jkl.SistemaBotica.api.unitMeasure.service;

import com.jkl.SistemaBotica.Utils;
import com.jkl.SistemaBotica.api.unitMeasure.entitie.UnitMeasure;
import com.jkl.SistemaBotica.api.unitMeasure.repository.UnitMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitMeasureService {
    @Autowired
    private UnitMeasureRepository unitMeasureRepository;

    public List<UnitMeasure> getAll() {
        return unitMeasureRepository.findAll();
    }

    public UnitMeasure getById(Long id) {
        return unitMeasureRepository.findById(id).orElse(null);
    }

    public UnitMeasure save(UnitMeasure unitMeasure) {
        unitMeasure.setIsDeleted(false);
        return unitMeasureRepository.save(unitMeasure);
    }

    public UnitMeasure update(UnitMeasure unitMeasure) {
        UnitMeasure unitMeasureToUpdate = unitMeasureRepository.findById(unitMeasure.getId()).orElse(null);

        Utils.updateField(unitMeasure.getName(), unitMeasureToUpdate::setName);
        Utils.updateField(unitMeasure.getShortName(), unitMeasureToUpdate::setShortName);
        Utils.updateField(unitMeasure.getIsDeleted(), unitMeasureToUpdate::setIsDeleted);

        unitMeasureRepository.save(unitMeasureToUpdate);

        return unitMeasureToUpdate;
    }

    public boolean delete(Long id) {
        com.jkl.SistemaBotica.api.unitMeasure.entitie.UnitMeasure unitMeasureToDelete = unitMeasureRepository.findById(id).orElse(null);
        if (unitMeasureToDelete != null && !unitMeasureToDelete.getIsDeleted()) {
            unitMeasureToDelete.setIsDeleted(true);
            unitMeasureRepository.save(unitMeasureToDelete);
            return true;
        }
        return false;
    }
}