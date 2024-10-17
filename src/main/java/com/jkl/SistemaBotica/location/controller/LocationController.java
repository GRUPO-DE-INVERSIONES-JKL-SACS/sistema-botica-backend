package com.jkl.SistemaBotica.location.controller;

import com.jkl.SistemaBotica.location.entitie.Location;
import com.jkl.SistemaBotica.location.service.LocationService;
import com.jkl.SistemaBotica.response.Response;
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
    public ResponseEntity<Response<Location>> getById(@PathVariable Long id) {
        Location location = locationService.getById(id);
        Response<Location> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Localización encontrada");
        response.setData(locationService.getById(id));

        if(location == null) {
            response.setStatus("Error");
            response.setMessage("Localización no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Location>> save(@RequestBody Location location) {
        Location auxLocation = locationService.save(location);
        Response<Location> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Localización guardada");
        response.setData(auxLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Location>> update(@RequestBody Location location, @PathVariable Long id) {
        location.setId(id);
        Location auxLocation = locationService.update(location);
        Response<Location> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Localización actualizada");
        response.setData(auxLocation);

        if(auxLocation == null) {
            response.setStatus("Error");
            response.setMessage("Localización no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable Long id) {
        boolean isDeleted = locationService.delete(id);

        Response<?> response = new Response<>();
        response.setStatus("Éxito");
        response.setMessage("Localización eliminada");

        if(!isDeleted) {
            response.setStatus("Error");
            response.setMessage("Localización no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
