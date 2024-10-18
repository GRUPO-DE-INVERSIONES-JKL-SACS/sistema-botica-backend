package com.jkl.SistemaBotica.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}