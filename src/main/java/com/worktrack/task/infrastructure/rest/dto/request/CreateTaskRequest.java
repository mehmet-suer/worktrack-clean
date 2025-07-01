package com.worktrack.task.infrastructure.rest.dto.request;

import com.worktrack.task.infrastructure.persistence.TaskJpaStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;

public record CreateTaskRequest(
        @NotBlank
        String title,

        String description,

        TaskJpaStatus status,

        @Nullable
        Long assignedTo
) {
}