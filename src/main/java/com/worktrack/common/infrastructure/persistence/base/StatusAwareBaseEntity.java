package com.worktrack.common.infrastructure.persistence.base;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class StatusAwareBaseEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @PrePersist
    protected void onCreation() {
        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }
}
