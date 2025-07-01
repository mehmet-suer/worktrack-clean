package com.worktrack.project.infrastructure.rest.dto.request;

import jakarta.validation.constraints.Size;

public record CreateProjectRequest(
        @Size(max = 255)
        String name,

        @Size(max = 255)
        String description,

        Long ownerId
) { }
