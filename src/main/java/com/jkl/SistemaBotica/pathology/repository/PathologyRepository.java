package com.jkl.SistemaBotica.pathology.repository;

import com.jkl.SistemaBotica.pathology.entitie.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathologyRepository extends JpaRepository<Pathology, Long> {
}
