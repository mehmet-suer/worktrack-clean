package com.worktrack.project.domain;


import com.worktrack.user.domain.UserId;

import java.util.Objects;

public class Project {

    private final ProjectId id;
    private final String name;
    private final String description;
    private final UserId ownerId;

    public Project(ProjectId id, String name, String description, UserId ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

    public ProjectId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public UserId ownerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}