package com.jkl.SistemaBotica.api.laboratory.controller;

import com.jkl.SistemaBotica.api.laboratory.entitie.Laboratory;
import com.jkl.SistemaBotica.api.laboratory.service.LaboratoryService;
import com.jkl.SistemaBotica.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/laboratory")
public class LaboratoryController {
    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping()
    public List<Laboratory> getAll() {
        return laboratoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Laboratory>> getById(@PathVariable Long id) {
        Laboratory laboratory = laboratoryService.getById(id);
        ResponseData<Laboratory> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Laboratorio encontrado");
        response.setData(laboratoryService.getById(id));

        if (laboratory == null) {
            response.setStatus("Error");
            response.setMessage("Laboratorio no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Laboratory>> save(@RequestBody Laboratory location) {
        Laboratory auxLaboratory = laboratoryService.save(location);
        ResponseData<Laboratory> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Laboratorio guardado");
        response.setData(auxLaboratory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Laboratory>> update(@RequestBody Laboratory location, @PathVariable Long id) {
        location.setId(id);
        Laboratory auxLaboratory = laboratoryService.update(location);
        ResponseData<Laboratory> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Laboratorio actualizado");
        response.setData(auxLaboratory);

        if (auxLaboratory == null) {
            response.setStatus("Error");
            response.setMessage("Laboratorio no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable Long id) {
        boolean isDeleted = laboratoryService.delete(id);

        ResponseData<?> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Laboratorio eliminado");

        if (!isDeleted) {
            response.setStatus("Error");
            response.setMessage("Laboratorio no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}