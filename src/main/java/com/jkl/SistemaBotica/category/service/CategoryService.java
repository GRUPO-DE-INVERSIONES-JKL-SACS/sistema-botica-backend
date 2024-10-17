package com.jkl.SistemaBotica.category.service;

import com.jkl.SistemaBotica.category.entitie.Category;
import org.springframework.beans.factory.annotation.Autowired;
import com.jkl.SistemaBotica.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jkl.SistemaBotica.Utils;

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

        Utils.updateField(category.getName(), categoryToUpdate::setName);
        Utils.updateField(category.getDescription(), categoryToUpdate::setDescription);
        Utils.updateField(category.getIsDeleted(), categoryToUpdate::setIsDeleted);

        categoryRepository.save(categoryToUpdate);

        return  categoryToUpdate;
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
