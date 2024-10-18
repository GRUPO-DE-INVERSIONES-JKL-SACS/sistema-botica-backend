package com.jkl.SistemaBotica.api.userRole;

import com.jkl.SistemaBotica.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleEntity> getAll() {
        return roleService.getAll();
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> save(@RequestBody RoleEntity role) {
        RoleEntity auxRole = roleService.save(role);
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Éxito");
        response.setMessage("Rol guardado");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> update(@RequestBody RoleEntity role, @PathVariable Long id) {
        role.setId(id);
        RoleEntity auxRole = roleService.update(role);

        ResponseMessage response = new ResponseMessage();
        response.setStatus("Éxito");
        response.setMessage("Rol actualizado");

        if (auxRole == null) {
            response.setStatus("Error");
            response.setMessage("Rol no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {

        RoleEntity auxRole = roleService.delete(id);

        ResponseMessage response = new ResponseMessage();
        response.setStatus("Éxito");
        response.setMessage("Rol eliminado");

        if (auxRole == null) {
            response.setStatus("Error");
            response.setMessage("Rol no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

}