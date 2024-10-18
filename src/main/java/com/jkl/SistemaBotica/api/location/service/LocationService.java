package com.jkl.SistemaBotica.api.location.service;

import com.jkl.SistemaBotica.Utils;
import com.jkl.SistemaBotica.api.location.entitie.Location;
import com.jkl.SistemaBotica.api.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public Location save(Location location) {
        location.setIsDeleted(false);
        return locationRepository.save(location);
    }

    public Location update(Location location) {
        Location locationToUpdate = locationRepository.findById(location.getId()).orElse(null);

        Utils.updateField(location.getName(), locationToUpdate::setName);
        Utils.updateField(location.getDescription(), locationToUpdate::setDescription);
        Utils.updateField(location.getIsDeleted(), locationToUpdate::setIsDeleted);

        locationRepository.save(locationToUpdate);

        return locationToUpdate;
    }

    public boolean delete(Long id) {
        Location locationToDelete = locationRepository.findById(id).orElse(null);
        if (locationToDelete != null && !locationToDelete.getIsDeleted()) {
            locationToDelete.setIsDeleted(true);
            locationRepository.save(locationToDelete);
            return true;
        }
        return false;
    }
}