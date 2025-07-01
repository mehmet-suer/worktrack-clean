package com.worktrack.project.infrastructure.persistence.entity;

import com.worktrack.common.infrastructure.persistence.base.AuditableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class ProjectJpaEntity extends AuditableBaseEntity {

    private String name;

    private String description;

    @Column(name = "owner_id", nullable = true)
    private Long owner;

    public ProjectJpaEntity() {
    }

    public ProjectJpaEntity(String name, String description, Long owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwner() {
        return owner;
    }
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
