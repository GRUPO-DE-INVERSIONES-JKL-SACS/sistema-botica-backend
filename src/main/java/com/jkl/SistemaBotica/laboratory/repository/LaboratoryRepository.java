package com.jkl.SistemaBotica.laboratory.repository;

import com.jkl.SistemaBotica.laboratory.entitie.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
}