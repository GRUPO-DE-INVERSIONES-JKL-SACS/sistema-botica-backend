package com.jkl.SistemaBotica.api.userPermission;

import com.jkl.SistemaBotica.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<PermissionEntity> getAll() {
        return permissionService.getAll();
    }

    @PostMapping
    public PermissionEntity save(@RequestBody PermissionDTO permissionDTO) {
        return permissionService.save(permissionDTO);
    }

    @PutMapping("/{id}")
    public PermissionEntity update(@RequestBody PermissionEntity permission, @PathVariable Long id) {
        permission.setId(id);
        return permissionService.update(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {

        boolean isDeleted = permissionService.delete(id);

        ResponseMessage response = new ResponseMessage();
        response.setStatus("Éxito");
        response.setMessage("Permiso eliminado");

        if (isDeleted) {
            response.setStatus("Error");
            response.setMessage("Permiso no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}