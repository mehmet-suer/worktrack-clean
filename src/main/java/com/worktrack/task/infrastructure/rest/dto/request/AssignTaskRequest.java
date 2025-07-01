package com.worktrack.task.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotNull;

public record AssignTaskRequest(
        @NotNull Long userId
) {}