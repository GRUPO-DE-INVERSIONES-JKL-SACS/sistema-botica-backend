package com.jkl.SistemaBotica.api.category.entitie;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "is_deleted")
    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("updated_at")
    private Date updatedAt;

}