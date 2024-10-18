package com.jkl.SistemaBotica.api.user;

import com.jkl.SistemaBotica.api.userRole.RoleEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String dni;
    private String username;
    private String email;
    private String phone;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}