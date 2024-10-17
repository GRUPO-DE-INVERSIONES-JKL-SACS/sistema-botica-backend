package com.jkl.SistemaBotica.unitMeasure.controller;

import com.jkl.SistemaBotica.response.Response;
import com.jkl.SistemaBotica.unitMeasure.entitie.UnitMeasure;
import com.jkl.SistemaBotica.unitMeasure.service.UnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/unit_measure")
public class UnitMeasureController {
    @Autowired
    private UnitMeasureService unitMeasureService;

    @GetMapping()
    public List<UnitMeasure> getAll() {
        return unitMeasureService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UnitMeasure>> getById(@PathVariable Long id) {
        UnitMeasure unitMeasure = unitMeasureService.getById(id);
        Response<com.jkl.SistemaBotica.unitMeasure.entitie.UnitMeasure> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Unidad de medida encontrada");
        response.setData(unitMeasureService.getById(id));

        if (unitMeasure == null) {
            response.setStatus("Error");
            response.setMessage("Unidad de medida no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<UnitMeasure>> save(@RequestBody UnitMeasure unitMeasure) {
        UnitMeasure auxUnitMeasure = unitMeasureService.save(unitMeasure);
        Response<UnitMeasure> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Unidad de medida guardada");
        response.setData(auxUnitMeasure);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<UnitMeasure>> update(@RequestBody UnitMeasure unitMeasure, @PathVariable Long id) {
        unitMeasure.setId(id);
        UnitMeasure auxUnitMeasure = unitMeasureService.update(unitMeasure);
        Response<UnitMeasure> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Unidad de medida actualizada");
        response.setData(auxUnitMeasure);

        if (auxUnitMeasure == null) {
            response.setStatus("Error");
            response.setMessage("Unidad de medida no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable Long id) {
        boolean isDeleted = unitMeasureService.delete(id);

        Response<?> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Unidad de medida eliminada");

        if (!isDeleted) {
            response.setStatus("Error");
            response.setMessage("Unidad de medida no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}