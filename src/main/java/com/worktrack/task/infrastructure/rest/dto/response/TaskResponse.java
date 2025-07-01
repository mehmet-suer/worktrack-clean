package com.worktrack.task.infrastructure.rest.dto.response;

public record TaskResponse(Long id,
                           String title,
                           String description,
                           String status,
                           String assignedTo) {}

