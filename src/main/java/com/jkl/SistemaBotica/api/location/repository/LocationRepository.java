package com.jkl.SistemaBotica.api.location.repository;

import com.jkl.SistemaBotica.api.location.entitie.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}