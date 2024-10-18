package com.jkl.SistemaBotica.api.category.controller;

import com.jkl.SistemaBotica.api.category.entitie.Category;
import com.jkl.SistemaBotica.api.category.service.CategoryService;
import com.jkl.SistemaBotica.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/v1/category")
//@PreAuthorize("denyAll()")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
//    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('READ')")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseData<Category>> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        ResponseData<Category> response = new ResponseData<>();
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
//    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseData<Category>> save(@RequestBody Category category) {
        Category auxCategory = categoryService.save(category);
        ResponseData<Category> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Categoria guardada");
        response.setData(auxCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> update(@RequestBody Category category, @PathVariable Long id) {
        category.setId(id);
        Category auxCategory = categoryService.update(category);
        ResponseData<Category> response = new ResponseData<>();
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
    public ResponseEntity<ResponseData<?>> delete(@PathVariable Long id) {
        boolean isDeleted = categoryService.delete(id);

        ResponseData<?> response = new ResponseData<>();
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