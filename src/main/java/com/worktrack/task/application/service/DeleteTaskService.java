package com.worktrack.task.application.service;

import com.worktrack.common.infrastructure.exception.EntityNotFoundException;
import com.worktrack.task.domain.TaskId;
import com.worktrack.task.application.port.in.DeleteTaskUseCase;
import com.worktrack.task.application.port.out.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskService implements DeleteTaskUseCase {
    private final TaskRepository taskRepository;
    private final TaskQueryService taskQueryService;

    public DeleteTaskService(TaskRepository taskRepository, TaskQueryService taskQueryService) {
        this.taskRepository = taskRepository;
        this.taskQueryService = taskQueryService;
    }

    @Override
    public void delete(TaskId taskId) {
        verifyTaskExists(taskId);
        taskRepository.delete(taskId);
    }

    private void verifyTaskExists(TaskId taskId) {
        taskQueryService.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Task with id " + taskId.value() + " does not exist."
                ));
    }
}
