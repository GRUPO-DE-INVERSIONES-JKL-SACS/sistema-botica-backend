package com.jkl.SistemaBotica.location.repository;

import com.jkl.SistemaBotica.location.entitie.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
