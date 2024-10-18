package com.jkl.SistemaBotica.api.pathology.controller;

import com.jkl.SistemaBotica.api.pathology.entitie.Pathology;
import com.jkl.SistemaBotica.api.pathology.service.PathologyService;
import com.jkl.SistemaBotica.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/pathology")
public class PathologyController {
    @Autowired
    private PathologyService pathologyService;

    @GetMapping()
    public List<Pathology> getAll() {
        return pathologyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Pathology>> getById(@PathVariable Long id) {
        Pathology pathology = pathologyService.getById(id);
        ResponseData<Pathology> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Patología encontrada");
        response.setData(pathologyService.getById(id));

        if (pathology == null) {
            response.setStatus("Error");
            response.setMessage("Patología no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Pathology>> save(@RequestBody Pathology pathology) {
        Pathology auxPathology = pathologyService.save(pathology);
        ResponseData<Pathology> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Patoloía guardada");
        response.setData(auxPathology);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Pathology>> update(@RequestBody Pathology pathology, @PathVariable Long id) {
        pathology.setId(id);
        Pathology auxPathology = pathologyService.update(pathology);
        ResponseData<Pathology> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Patoloía actualizada");
        response.setData(auxPathology);

        if (auxPathology == null) {
            response.setStatus("Error");
            response.setMessage("Patoloía no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable Long id) {
        boolean isDeleted = pathologyService.delete(id);

        ResponseData<?> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Patoloía eliminada");

        if (!isDeleted) {
            response.setStatus("Error");
            response.setMessage("Patoloía no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}