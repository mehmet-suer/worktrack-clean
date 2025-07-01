package com.worktrack.common.infrastructure.persistence.base;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    @Column(name = "version")
    protected Integer version;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }
}
