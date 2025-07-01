package com.worktrack.task.application.service;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.project.application.port.service.ProjectQueryService;
import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.application.port.in.CreateTaskUseCase;
import com.worktrack.task.application.port.out.TaskRepository;
import com.worktrack.task.domain.Task;
import com.worktrack.user.application.port.out.UserServicePort;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepository taskRepository;
    private final UserServicePort userServiceClient;
    private final ProjectQueryService projectQueryService;

    public CreateTaskService(TaskRepository taskRepository,
                             UserServicePort userServiceClient,
                             ProjectQueryService projectQueryService) {
        this.taskRepository = taskRepository;
        this.userServiceClient = userServiceClient;
        this.projectQueryService =  projectQueryService;
    }


    @Override
    public Task createTask(ProjectId projectId, Task domain) {
        validateTask(projectId, domain);
        taskRepository.save(domain);
        return domain;
    }

    private void validateTask(ProjectId projectId, Task domain) {
        if (projectId != null) {
            projectQueryService.getProjectByIdForced(projectId);
        }

        if (domain.getAssignedTo() != null) {
            var user = userServiceClient.getUserById(domain.getAssignedTo())
                    .orElseThrow( () -> new EntityNotFoundException("User not found with ID: " + domain.getAssignedTo().value()));
        }
    }
}
