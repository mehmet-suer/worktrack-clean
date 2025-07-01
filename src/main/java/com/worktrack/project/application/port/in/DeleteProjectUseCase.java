package com.worktrack.project.application.port.in;

import com.worktrack.project.domain.ProjectId;

public interface DeleteProjectUseCase {
    void delete(ProjectId projectId);
}

