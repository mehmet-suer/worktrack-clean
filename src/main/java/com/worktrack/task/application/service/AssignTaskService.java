package com.worktrack.task.application.service;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.task.application.port.in.AssignTaskUseCase;
import com.worktrack.task.application.port.out.TaskRepository;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.domain.TaskStatus;
import com.worktrack.user.application.port.out.UserServicePort;
import com.worktrack.user.domain.Role;
import com.worktrack.user.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class AssignTaskService implements AssignTaskUseCase {

    private final TaskRepository taskRepository;
    private final UserServicePort userServiceClient;

    public AssignTaskService(TaskRepository taskRepository,
                             UserServicePort userServiceClient) {
        this.taskRepository = taskRepository;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public void assign(TaskId taskId, UserId userId) {
        var task = taskRepository.findByIdForced(taskId);
        var user = userServiceClient.getUserById(userId).orElseThrow( () -> new EntityNotFoundException("User not found with ID: " + userId.value()));

        if (task.getTaskStatus() != TaskStatus.TODO) {
            throw new IllegalStateException("Task is not in TODO status");
        }

        if (user.role() != Role.EMPLOYEE) {
            throw new IllegalStateException("User is not an employee");
        }

        task.assignTo(user.id());
        taskRepository.save(task);
    }
}
