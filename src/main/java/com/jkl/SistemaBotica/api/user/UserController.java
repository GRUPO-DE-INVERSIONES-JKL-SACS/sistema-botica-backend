package com.jkl.SistemaBotica.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public UserEntity save(UserEntity user) {
        return userService.save(user);
    }

    @PutMapping
    public UserEntity update(UserEntity user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}