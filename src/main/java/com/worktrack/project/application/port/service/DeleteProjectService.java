package com.worktrack.project.application.port.service;

import com.worktrack.project.application.port.in.DeleteProjectUseCase;
import com.worktrack.project.application.port.out.ProjectRepository;
import com.worktrack.project.domain.ProjectId;
import org.springframework.stereotype.Service;

@Service
public class DeleteProjectService implements DeleteProjectUseCase {

    private final ProjectRepository projectRepository;

    public DeleteProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void delete(ProjectId projectId) {
         projectRepository.delete(projectId);
    }
}
