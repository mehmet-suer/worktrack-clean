package com.worktrack.project.application.port.out;

import com.worktrack.project.domain.Project;
import com.worktrack.project.domain.ProjectId;

import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(ProjectId id);
    Project save(Project project);
    void delete(ProjectId id);
}
