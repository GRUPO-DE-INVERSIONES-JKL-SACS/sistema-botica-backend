package com.jkl.SistemaBotica.api.category.repository;

import com.jkl.SistemaBotica.api.category.entitie.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}