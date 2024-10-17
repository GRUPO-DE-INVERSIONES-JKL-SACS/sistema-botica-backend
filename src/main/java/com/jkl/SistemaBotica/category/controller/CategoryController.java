package com.jkl.SistemaBotica.category.controller;

import com.jkl.SistemaBotica.category.entitie.Category;
import com.jkl.SistemaBotica.category.service.CategoryService;
import com.jkl.SistemaBotica.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Category>> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        Response<Category> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Categoria encontrada");
        response.setData(category);

        if (category == null) {
            response.setStatus("Error");
            response.setMessage("Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Category>> save(@RequestBody Category category) {
        Category auxCategory = categoryService.save(category);
        Response<Category> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Categoria guardada");
        response.setData(auxCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Category>> update(@RequestBody Category category, @PathVariable Long id) {
        category.setId(id);
        Category auxCategory = categoryService.update(category);
        Response<Category> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Categoria actualizada");
        response.setData(auxCategory);

        if (auxCategory == null) {
            response.setStatus("Error");
            response.setMessage("Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable Long id) {
        boolean isDeleted = categoryService.delete(id);

        Response<?> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Categoría eliminada");

        if (!isDeleted) {
            response.setStatus("Error");
            response.setMessage("Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}