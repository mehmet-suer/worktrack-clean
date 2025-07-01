package com.worktrack.project.application.port.out;

import com.worktrack.project.domain.Project;
import com.worktrack.project.domain.ProjectId;

import java.util.Optional;

public interface ProjectQueryPort {
    Project getProjectByIdForced(ProjectId projectId);
    Optional<Project> getProjectById(ProjectId projectId);
}
