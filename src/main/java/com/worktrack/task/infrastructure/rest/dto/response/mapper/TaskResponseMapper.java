package com.worktrack.task.infrastructure.rest.dto.response.mapper;

import com.worktrack.task.domain.Task;
import com.worktrack.task.infrastructure.rest.dto.response.TaskResponse;

public class TaskResponseMapper {
    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId() != null ? task.getId().value() : null,
                task.getTitle(),
                task.getDescription(),
                task.getTaskStatus() != null ? task.getTaskStatus().name() : null,
                task.getAssignedTo() != null ? task.getAssignedTo().value().toString() : null
        );
    }
}
