package com.jkl.SistemaBotica.api.userPermission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jkl.SistemaBotica.api.userRole.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "can_read")
    @JsonProperty("can_read")
    private Boolean canRead;

    @Column(name = "can_write")
    @JsonProperty("can_write")
    private Boolean canWrite;

    @Column(name = "can_delete")
    @JsonProperty("can_delete")
    private Boolean canDelete;

    @Column(name = "can_update")
    @JsonProperty("can_update")
    private Boolean canUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    private RoleEntity role;
}