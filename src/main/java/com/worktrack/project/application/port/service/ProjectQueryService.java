package com.worktrack.project.application.port.service;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.project.application.port.out.ProjectRepository;
import com.worktrack.project.domain.Project;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.project.application.port.out.ProjectQueryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectQueryService implements ProjectQueryPort {

    private final ProjectRepository projectRepository;

    public ProjectQueryService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProjectByIdForced(ProjectId projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public Optional<Project> getProjectById(ProjectId projectId) {
        return projectRepository.findById(projectId);
    }


}
