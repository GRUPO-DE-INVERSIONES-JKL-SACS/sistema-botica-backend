package com.jkl.SistemaBotica.api.location.controller;

import com.jkl.SistemaBotica.api.location.entitie.Location;
import com.jkl.SistemaBotica.api.location.service.LocationService;
import com.jkl.SistemaBotica.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping()
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Location>> getById(@PathVariable Long id) {
        Location location = locationService.getById(id);
        ResponseData<Location> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Localización encontrada");
        response.setData(locationService.getById(id));

        if (location == null) {
            response.setStatus("Error");
            response.setMessage("Localización no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Location>> save(@RequestBody Location location) {
        Location auxLocation = locationService.save(location);
        ResponseData<Location> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Localización guardada");
        response.setData(auxLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Location>> update(@RequestBody Location location, @PathVariable Long id) {
        location.setId(id);
        Location auxLocation = locationService.update(location);
        ResponseData<Location> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Localización actualizada");
        response.setData(auxLocation);

        if (auxLocation == null) {
            response.setStatus("Error");
            response.setMessage("Localización no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable Long id) {
        boolean isDeleted = locationService.delete(id);

        ResponseData<?> response = new ResponseData<>();
        response.setStatus("Éxito");
        response.setMessage("Localización eliminada");

        if (!isDeleted) {
            response.setStatus("Error");
            response.setMessage("Localización no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}