package com.jkl.SistemaBotica.api.userRole;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jkl.SistemaBotica.api.userPermission.PermissionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean isDeleted;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Date createdAt;

    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Date updatedAt;


    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<PermissionEntity> permissions;
}