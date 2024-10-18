package com.jkl.SistemaBotica.api.category.service;

import com.jkl.SistemaBotica.Utils;
import com.jkl.SistemaBotica.api.category.entitie.Category;
import com.jkl.SistemaBotica.api.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        category.setIsDeleted(false);
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        Category categoryToUpdate = categoryRepository.findById(category.getId()).orElse(null);

        if (categoryToUpdate == null) {
            return null;
        }

        Utils.updateField(category.getName(), categoryToUpdate::setName);
        Utils.updateField(category.getDescription(), categoryToUpdate::setDescription);
        Utils.updateField(category.getIsDeleted(), categoryToUpdate::setIsDeleted);

        categoryRepository.save(categoryToUpdate);

        return categoryToUpdate;
    }

    public boolean delete(Long id) {
        Category categoryToDelete = categoryRepository.findById(id).orElse(null);
        if (categoryToDelete != null && !categoryToDelete.getIsDeleted()) {
            categoryToDelete.setIsDeleted(true);
            categoryRepository.save(categoryToDelete);
            return true;
        }
        return false;
    }
}