package com.jkl.SistemaBotica.api.pathology.repository;

import com.jkl.SistemaBotica.api.pathology.entitie.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathologyRepository extends JpaRepository<Pathology, Long> {
}