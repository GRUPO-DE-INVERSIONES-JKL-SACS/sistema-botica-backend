package com.jkl.SistemaBotica.category.repository;

import com.jkl.SistemaBotica.category.entitie.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
