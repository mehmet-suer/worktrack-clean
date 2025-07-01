package com.worktrack.project.application.port.service;

import com.worktrack.project.application.port.in.CreateProjectUseCase;
import com.worktrack.project.application.port.out.ProjectRepository;
import com.worktrack.project.domain.Project;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectService implements CreateProjectUseCase {

    private final ProjectRepository projectRepository;

    public CreateProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
}
