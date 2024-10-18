package com.jkl.SistemaBotica.api.userPermission;

import com.jkl.SistemaBotica.Utils;
import com.jkl.SistemaBotica.api.userRole.RoleEntity;
import com.jkl.SistemaBotica.api.userRole.RoleRepository;
import com.jkl.SistemaBotica.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<PermissionEntity> getAll() {
        return permissionRepository.findAll();
    }

    public PermissionEntity save(PermissionDTO permissionDTO) {
        PermissionEntity permission = new PermissionEntity();
        permission.setName(permissionDTO.getName());
        permission.setCanRead(permissionDTO.getCanRead());
        permission.setCanWrite(permissionDTO.getCanWrite());
        permission.setCanDelete(permissionDTO.getCanDelete());
        permission.setCanUpdate(permissionDTO.getCanUpdate());

        RoleEntity role = roleRepository.findById(permissionDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found with id: " + permissionDTO.getRoleId()));

        permission.setRole(role);

        return permissionRepository.save(permission);
    }

    public PermissionEntity update(PermissionEntity permission) {
        PermissionEntity permissionToUpdate = permissionRepository.findById(permission.getId()).orElse(null);

        if (permissionToUpdate == null) {
            return null;
        }

        Utils.updateField(permission.getName(), permissionToUpdate::setName);
        Utils.updateField(permission.getCanRead(), permissionToUpdate::setCanRead);
        Utils.updateField(permission.getCanWrite(), permissionToUpdate::setCanWrite);
        Utils.updateField(permission.getCanUpdate(), permissionToUpdate::setCanUpdate);
        Utils.updateField(permission.getCanDelete(), permissionToUpdate::setCanDelete);

        return permissionRepository.save(permissionToUpdate);
    }

    public boolean delete(Long id) {
        PermissionEntity permissionToDelete = permissionRepository.findById(id).orElse(null);

        return permissionToDelete == null;
    }
}