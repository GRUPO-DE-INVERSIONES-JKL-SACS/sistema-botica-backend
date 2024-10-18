package com.jkl.SistemaBotica.api.unitMeasure.repository;

import com.jkl.SistemaBotica.api.unitMeasure.entitie.UnitMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitMeasureRepository extends JpaRepository<UnitMeasure, Long> {
}