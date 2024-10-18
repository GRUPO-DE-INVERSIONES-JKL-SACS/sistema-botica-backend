package com.jkl.SistemaBotica.api.userRole;

import com.jkl.SistemaBotica.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    public RoleEntity save(RoleEntity role) {
        role.setIsDeleted(false);
        return roleRepository.save(role);
    }

    public RoleEntity update(RoleEntity role) {
        RoleEntity roleToUpdate = roleRepository.findById(role.getId()).orElse(null);

        if (roleToUpdate == null) {
            return null;
        }

        Utils.updateField(role.getName(), roleToUpdate::setName);
        Utils.updateField(role.getDescription(), roleToUpdate::setDescription);

        return roleRepository.save(roleToUpdate);
    }

    public RoleEntity delete(Long id) {
        RoleEntity roleToDelete = roleRepository.findById(id).orElse(null);

        if (roleToDelete == null || roleToDelete.getIsDeleted()) {
            return null;
        }

        Utils.updateField(true, roleToDelete::setIsDeleted);

        return roleRepository.save(roleToDelete);
    }

}