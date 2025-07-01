package com.worktrack.task.infrastructure.rest.dto.request;

import com.worktrack.project.domain.ProjectId;
import com.worktrack.task.domain.TaskStatus;
import com.worktrack.task.domain.Task;
import com.worktrack.user.domain.UserId;


public class CreateTaskRequestMapper {

    public static Task toDomain(CreateTaskRequest request , Long projectId) {
        return new Task(
                null,
                request.title(),
                request.description(),
                request.status() != null ? TaskStatus.valueOf(request.status().name()) : null,
                projectId != null ? new ProjectId(projectId) : null,
                request.assignedTo() != null ? new UserId (request.assignedTo()) : null
        );
    }

}
